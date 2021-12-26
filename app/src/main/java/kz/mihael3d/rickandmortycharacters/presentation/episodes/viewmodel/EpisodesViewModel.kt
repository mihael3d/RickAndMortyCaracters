package kz.mihael3d.rickandmortycharacters.presentation.episodes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesRepository
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val episodesRepository: EpisodesRepository)
    : ViewModel() {
        private lateinit var _episodesFlow: Flow<PagingData<Episode>>

        val episodesFlow: Flow<PagingData<Episode>>
        get() = _episodesFlow

    init {
        getAllEpisodes()
    }

    private fun getAllEpisodes() = viewModelScope.launch {
        _episodesFlow =  episodesRepository.getAllEpisodes().cachedIn(viewModelScope)
    }
}