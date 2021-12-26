package kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesRepository
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode
import kz.mihael3d.rickandmortycharacters.presentation.Loading
import kz.mihael3d.rickandmortycharacters.presentation.Ready
import kz.mihael3d.rickandmortycharacters.presentation.SearchState
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel  @Inject constructor(
    private val episodesRepository: EpisodesRepository
    ) : ViewModel() {

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>>
        get() = _episodes

    private val _searchState = MutableLiveData<SearchState>()
    val searchState: LiveData<SearchState>
        get() = _searchState

    fun setEpisodeId(id:  String){
        _searchState.value = Loading
        getAllEpisodes(id)
    }

    private fun getAllEpisodes(id: String) = viewModelScope.launch{
        val episode = episodesRepository.getEpisodesById(id)
        delay(1000)
        _episodes.value = episode
        _searchState.value = Ready
    }
}