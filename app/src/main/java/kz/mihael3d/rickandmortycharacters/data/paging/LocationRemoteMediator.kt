package kz.mihael3d.rickandmortycharacters.data.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kz.mihael3d.rickandmortycharacters.data.api.LocationsApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location
import kz.mihael3d.rickandmortycharacters.data.model.entites.LocationPageKey
import java.lang.Exception



@ExperimentalPagingApi
class LocationRemoteMediator(val service: LocationsApi, val db: AppDB): RemoteMediator<Int, Location>() {
    private val locationDao = db.locationDao()
    private val pageKeyDao = db.locationPageKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Location>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remotetKeyEpisode: LocationPageKey? = db.withTransaction {
                        if( lastItem?.id !=null ) {
                            pageKeyDao.getNextPageKey(lastItem.id)
                        } else null
                    }

                    if (remotetKeyEpisode?.nextPageUrl == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    val uri = Uri.parse(remotetKeyEpisode.nextPageUrl)
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
                    it.page = loadKey
                    pageKeyDao.insertOrReplace(LocationPageKey( it.id, pageInfo?.next))
                }
                results?.let { locationDao.insertAll(it) }
            }

            MediatorResult.Success(endOfPaginationReached = resBody?.pageInfo?.next == null)

        }catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
















