package kz.mihael3d.rickandmortycharacters.screens.main.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesRepository
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val rpisodesRepository: EpisodesRepository)
    : ViewModel() {
        private lateinit var _episodesFlow: Flow<PagingData<Episode>>

        val episodesFlow: Flow<PagingData<Episode>>
        get() = _episodesFlow

    init {
        getAllEpisodes()
    }

    private fun getAllEpisodes() = viewModelScope.launch {
        _episodesFlow =  rpisodesRepository.getAllEpisodes().cachedIn(viewModelScope)
    }
}