package kz.mihael3d.rickandmortycharacters.data.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kz.mihael3d.rickandmortycharacters.data.api.EpisodeApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.model.entites.PageKey
import java.lang.Exception

@ExperimentalPagingApi
class EpisodeRemoteMediator(val servise: EpisodeApi, val db: AppDB): RemoteMediator<Int, Episode>() {
    private val episodeDao = db.episodeDao()
    private val pageKeyDao = db.pageKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Episode>
    ): MediatorResult {
       return try {
           val loadKey = when (loadType) {
               LoadType.REFRESH -> 1
               LoadType.PREPEND ->
                   return MediatorResult.Success(endOfPaginationReached = true)
               LoadType.APPEND -> {
                   val lastItem = state.lastItemOrNull()
                   val remotetKey: PageKey? = db.withTransaction {
                       if( lastItem?.id !=null ) {
                           pageKeyDao.getNextPageKey(lastItem.id)
                       } else null
                   }

                   if (remotetKey?.nextPageUrl == null) {
                       return MediatorResult.Success(endOfPaginationReached = true)
                   }

                   val uri = Uri.parse(remotetKey.nextPageUrl)
                   val nextPageQuery = uri.getQueryParameter("page")
                   nextPageQuery?.toInt()
               }
           }

           val response = servise.getAllEpisodes(loadKey?: 1)
           val resBody = response.body()
           val pageInfo = resBody?.pageInfo
           val results = resBody?.results

           db.withTransaction {
               if (loadType == LoadType.PREPEND) {
                   episodeDao.clearAll()
                   pageKeyDao.clearAll()
               }

               results?.forEach {
                   it.page = loadKey
                   pageKeyDao.insertOrReplace(PageKey( it.id, pageInfo?.next))
               }
               results?.let { episodeDao.insertAll(it) }
           }

           MediatorResult.Success(endOfPaginationReached = resBody?.pageInfo?.next == null)

       }catch (e: Exception) {
           MediatorResult.Error(e)
       }
    }
}