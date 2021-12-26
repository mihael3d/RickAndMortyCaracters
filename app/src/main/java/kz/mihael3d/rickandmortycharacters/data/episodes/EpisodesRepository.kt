package kz.mihael3d.rickandmortycharacters.data.repository.episodes

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mihael3d.rickandmortycharacters.data.episodes.remote.EpisodeApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeEntity
import kz.mihael3d.rickandmortycharacters.data.episodes.pagination.EpisodeRemoteMediator
import kz.mihael3d.rickandmortycharacters.data.episodes.toEntity
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode
import kz.mihael3d.rickandmortycharacters.domain.episodes.toModel
import kz.mihael3d.rickandmortycharacters.domain.locations.models.Location
import kz.mihael3d.rickandmortycharacters.domain.locations.toLocation
import javax.inject.Inject

interface EpisodesRepository {

    /**
     * @return Flow of PagingData [Episode].
     */
    fun getAllEpisodes(): Flow<PagingData<Episode>>

    suspend fun  getEpisodesById(id: String):List<Episode>
}

/**
 * Repository providing data about [Episode]
 */
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
        .map {pagingData -> pagingData.map { episode -> episode.toModel()}  }

    override suspend fun getEpisodesById(id: String): List<Episode> =
         service.getEpisodesById(id).map{
             episode -> episode.toEntity().toModel()
         }

}

