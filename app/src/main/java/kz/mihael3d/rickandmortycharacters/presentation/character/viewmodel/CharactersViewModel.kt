package kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.repository.charaters.CharactersRepository
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.presentation.SearchState
import javax.inject.Inject

private const val TEXT_ENTERED_DEBOUNCE_MILLIS = 500L

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    companion object {
        const val KEY_SUBREDDIT = "characters"
        const val DEFAULT_SUBREDDIT = ""
    }

    private val _searchState = MutableLiveData<SearchState>()
    val searchState : LiveData<SearchState>
    get() = _searchState

    private val queryFlow = MutableStateFlow("")


    private lateinit var _charcersFlow: Flow<PagingData<Character>>
    val charcersFlow: Flow<PagingData<Character>>
    get() = _charcersFlow

    init {

        if (!savedStateHandle.contains(KEY_SUBREDDIT)) {
            savedStateHandle.set(KEY_SUBREDDIT, DEFAULT_SUBREDDIT)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val posts = savedStateHandle.getLiveData<String>(KEY_SUBREDDIT)
        .asFlow()
        .flatMapLatest { charactersRepository.getCharactersByNameWithCashing(it) }
        // cachedIn() shares the paging state across multiple consumers of posts,
        // e.g. different generations of UI across rotation config change
        .cachedIn(viewModelScope)


    fun showSubreddit(subreddit: String) {
        if (!shouldShowSubreddit(subreddit)) return
        savedStateHandle.set(KEY_SUBREDDIT, subreddit)
    }

    private fun shouldShowSubreddit(subreddit: String): Boolean {
        return savedStateHandle.get<String>(KEY_SUBREDDIT) != subreddit
    }

    fun onNewQuery(query: String) {
//        getCharacersByName(query)

//       queryFlow.value = query
    }

    private fun getAllCharacers() = viewModelScope.launch {
        _charcersFlow =  charactersRepository.getAllCharactersWithCashing().cachedIn(viewModelScope)
    }

    private fun getCharacersByName(name: String) = viewModelScope.launch {
        _charcersFlow =  charactersRepository.getCharactersByNameWithCashing(name).cachedIn(viewModelScope)
    }
}