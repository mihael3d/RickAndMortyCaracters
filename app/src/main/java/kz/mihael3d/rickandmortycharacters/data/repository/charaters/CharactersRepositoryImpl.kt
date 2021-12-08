package kz.mihael3d.rickandmortycharacters.data.repository.charaters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.mihael3d.rickandmortycharacters.data.api.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.model.Character
import kz.mihael3d.rickandmortycharacters.data.paging.CharactersPagingDataSourse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val service: CharacterApi) :
    CharactersRepository {
    override suspend fun getAllChartacters(): Flow<PagingData<Character>> =
       Pager( config = PagingConfig(pageSize = 20, prefetchDistance = 2),
       pagingSourceFactory = {CharactersPagingDataSourse(service)}
       ).flow

}