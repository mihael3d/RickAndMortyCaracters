package kz.mihael3d.rickandmortycharacters.data.repository.locations

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location

interface LocationsRepository {
    fun getAllLocations(): Flow<PagingData<Location>>
}