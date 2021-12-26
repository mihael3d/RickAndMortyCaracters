package kz.mihael3d.rickandmortycharacters.data.locations

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mihael3d.rickandmortycharacters.data.locations.remote.LocationsApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.locations.local.LocationRemoteMediator
import kz.mihael3d.rickandmortycharacters.domain.locations.models.Location
import kz.mihael3d.rickandmortycharacters.domain.locations.toLocation
import javax.inject.Inject


interface LocationsRepository {

    /**
     * @return Flow of PagingData [Location].
     */
        fun getAllLocations(): Flow<PagingData<Location>>
}


/**
 * Repository providing data about [Location]
 */
@ExperimentalPagingApi
class LocationsRepositoryImpl
    @Inject constructor(
        private val service: LocationsApi,
        private val db: AppDB
    ) : LocationsRepository {

    override fun getAllLocations(): Flow<PagingData<Location>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        remoteMediator = LocationRemoteMediator(service, db)
    ) {
        db.locationDao().getLocationsPagingSource()
    }.flow
        .map { pagingData -> pagingData.map { location -> location.toLocation()} }

}