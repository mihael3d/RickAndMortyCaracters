package kz.mihael3d.rickandmortycharacters.data.repository.charaters

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.mihael3d.rickandmortycharacters.data.model.Character

interface CharactersRepository {
    suspend fun getAllChartacters(): Flow<PagingData<Character>>
}