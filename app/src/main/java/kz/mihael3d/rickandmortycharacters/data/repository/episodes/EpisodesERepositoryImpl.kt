package kz.mihael3d.rickandmortycharacters.data.repository.episodes

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mihael3d.rickandmortycharacters.data.api.EpisodeApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.paging.EpisodeRemoteMediator
import javax.inject.Inject

@ExperimentalPagingApi
class EpisodesERepositoryImpl
    @Inject constructor(
        private val service: EpisodeApi,
        private val db: AppDB
    ) : EpisodesRepository {

    override fun getAllEpisodes(): Flow<PagingData<Episode>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        remoteMediator = EpisodeRemoteMediator(service, db)
    ) {
        db.episodeDao().getEpisodesPagingSource()
    }.flow



}