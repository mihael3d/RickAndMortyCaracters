package kz.mihael3d.rickandmortycharacters.data.pagination

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity
import kz.mihael3d.rickandmortycharacters.data.characters.local.toEntity
import kz.mihael3d.rickandmortycharacters.data.characters.remote.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import java.lang.Exception

@ExperimentalPagingApi
class CharacterRemoteMediator(val service: CharacterApi, val db: AppDB, val name: String): RemoteMediator<Int, CharacterEntity>() {
    private val characterDao = db.charactersDao()
    private val pageKeyDao = db.charactersPageKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKeyEpisode: CharactersPageKey? = db.withTransaction {
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

//            val response = service.getAllCharacters(loadKey?: 1)
            val response = service.searchCharacter(name = name, page = loadKey?: 1)

            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val results = resBody?.results

            val uri = Uri.parse(pageInfo?.next)
            val nextPageNumber = uri.getQueryParameter("page")?.toInt()



            db.withTransaction {
                if (loadType == LoadType.PREPEND) {
                    characterDao.clearAll()
                    pageKeyDao.clearAll()
                }
                results?.forEach {
                    pageKeyDao.insertOrReplace(CharactersPageKey( it.id, pageInfo?.next))
                }

                val characterEntities = results?.map {
                    it.toEntity()
                }

                characterEntities?.forEach {

                    it.page = nextPageNumber

                }
                characterEntities?.let { characterDao.insertAll(it) }
            }

            MediatorResult.Success(endOfPaginationReached = resBody?.pageInfo?.next == null)

        }catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}