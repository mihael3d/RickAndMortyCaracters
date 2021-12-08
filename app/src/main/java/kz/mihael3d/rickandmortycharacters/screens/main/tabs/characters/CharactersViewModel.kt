package kz.mihael3d.rickandmortycharacters.screens.main.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.model.Character
import kz.mihael3d.rickandmortycharacters.data.repository.charaters.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
    ) : ViewModel() {

    private lateinit var _charcersFlow: Flow<PagingData<Character>>
    val charcersFlow: Flow<PagingData<Character>>
    get() = _charcersFlow

    init {
        getAllCharacers()
    }

    private fun getAllCharacers() = viewModelScope.launch {
        _charcersFlow =  charactersRepository.getAllChartacters().cachedIn(viewModelScope)
    }

}