package kz.mihael3d.rickandmortycharacters.data.repository.episodes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode

interface EpisodesRepository {
    fun getAllEpisodes(): Flow<PagingData<Episode>>
}