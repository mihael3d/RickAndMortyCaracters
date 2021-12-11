package kz.mihael3d.rickandmortycharacters.data.repository.locations

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.mihael3d.rickandmortycharacters.data.api.LocationsApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location
import kz.mihael3d.rickandmortycharacters.data.paging.LocationRemoteMediator
import javax.inject.Inject

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
        db.locationDao().pagingSource()
    }.flow

}