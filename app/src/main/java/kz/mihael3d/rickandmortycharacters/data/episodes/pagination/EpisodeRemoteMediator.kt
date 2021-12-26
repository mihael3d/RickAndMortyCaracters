package kz.mihael3d.rickandmortycharacters.data.episodes.pagination

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kz.mihael3d.rickandmortycharacters.data.episodes.remote.EpisodeApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeEntity
import kz.mihael3d.rickandmortycharacters.data.episodes.toEntity
import java.lang.Exception

@ExperimentalPagingApi
class EpisodeRemoteMediator(val servise: EpisodeApi, val db: AppDB): RemoteMediator<Int, EpisodeEntity>() {
    private val episodeDao = db.episodeDao()
    private val pageKeyDao = db.episodePageKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {
       return try {
           val loadKey = when (loadType) {
               LoadType.REFRESH -> 1
               LoadType.PREPEND ->
                   return MediatorResult.Success(endOfPaginationReached = true)
               LoadType.APPEND -> {
                   val lastItem = state.lastItemOrNull()
                   val remotetKeyEpisode: EpisodePageKey? = db.withTransaction {
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
                   pageKeyDao.insertOrReplace(EpisodePageKey( it.id, pageInfo?.next))
               }
               val episodesEntities = results?.map {it.toEntity() }
               episodesEntities?.forEach{it.page = loadKey }
               episodesEntities?.let { episodeDao.insertAll(it)
                  }
           }

           MediatorResult.Success(endOfPaginationReached = resBody?.pageInfo?.next == null)

       }catch (e: Exception) {
           MediatorResult.Error(e)
       }
    }
}
