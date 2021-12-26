package kz.mihael3d.rickandmortycharacters.data.locations.local

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kz.mihael3d.rickandmortycharacters.data.locations.remote.LocationsApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import java.lang.Exception



@ExperimentalPagingApi
class LocationRemoteMediator(val service: LocationsApi, val db: AppDB): RemoteMediator<Int, LocationEntity>() {
    private val locationDao = db.locationDao()
    private val pageKeyDao = db.locationPageKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKeyEpisode: LocationPageKey? = db.withTransaction {
                        if( lastItem?.id !=null ) {
                            pageKeyDao.getNextPageKey(lastItem.id)
                        } else null
                    }

                    if (remoteKeyEpisode?.nextPageUrl == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    val uri = Uri.parse(remoteKeyEpisode.nextPageUrl)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextPageQuery?.toInt()
                }
            }

            val response = service.getAllLocations(loadKey?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val results = resBody?.results

            db.withTransaction {
                if (loadType == LoadType.PREPEND) {
                    locationDao.clearAll()
                    pageKeyDao.clearAll()
                }

                results?.forEach {
                    pageKeyDao.insertOrReplace(LocationPageKey( it.id, pageInfo?.next))
                }
                val locationEntities = results?.map {it.toEntity() }
                locationEntities?.forEach{it.page = loadKey }
                locationEntities?.let { locationDao.insertAll(it) }
            }

            MediatorResult.Success(endOfPaginationReached = resBody?.pageInfo?.next == null)

        }catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
















