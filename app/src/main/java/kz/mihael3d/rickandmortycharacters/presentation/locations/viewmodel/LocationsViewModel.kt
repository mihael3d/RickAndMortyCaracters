package kz.mihael3d.rickandmortycharacters.presentation.locations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.locations.LocationsRepository
import kz.mihael3d.rickandmortycharacters.domain.locations.models.Location
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val locationsRepository: LocationsRepository)
    : ViewModel() {
    private lateinit var _locationsFlow: Flow<PagingData<Location>>

    val locationsFlow: Flow<PagingData<Location>>
        get() = _locationsFlow

    init {
        getAllEpisodes()
    }

    private fun getAllEpisodes() = viewModelScope.launch {
        _locationsFlow =  locationsRepository.getAllLocations().cachedIn(viewModelScope)
    }
}