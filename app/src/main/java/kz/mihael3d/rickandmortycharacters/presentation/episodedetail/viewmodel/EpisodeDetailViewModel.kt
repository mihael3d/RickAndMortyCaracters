package kz.mihael3d.rickandmortycharacters.presentation.episodedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.data.repository.charaters.CharactersRepository
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesRepository
import kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel.CharactersResult
import kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel.CharactersResult.EmptyQuery
import kz.mihael3d.rickandmortycharacters.utils.OperationResult
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository,
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<CharactersResult>(CharactersResult.EmptyResult())

    val characters: StateFlow<CharactersResult>
        get() = _characters


    fun setCharactersList(charactersUrls: List<String>) {
        viewModelScope.launch {
//           val characters = async{charactersRepository.getCharactersList(charactersUrls)}

            val charactersList = async {handleRequestCharacters(charactersUrls)}
            val charactersList2 =  handleRequestCharacters(charactersUrls)

//            _characters = charactersList
//            _characters.value = charactersList2
//            _characters.emit(charactersList.await())
            _characters.emit(charactersList2)
//            _characters.emit(charactersList2)
        }
    }

    private suspend fun handleRequestCharacters(charactersUrls: List<String>): CharactersResult {

        return when (val charactersResult = charactersRepository.getCharacters(charactersUrls)) {
            is OperationResult.Error -> CharactersResult.ErrorResult(IllegalAccessException("Get characters from server error!"))
            is OperationResult.Success -> if (charactersResult.result.isEmpty()) {
                EmptyQuery()
            } else {
                CharactersResult.ValidResult(charactersResult.result)
            }
        }
    }

}