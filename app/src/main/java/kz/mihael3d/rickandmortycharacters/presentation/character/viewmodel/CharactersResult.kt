package kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel

import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character

/**
 * Class containing the result of the [Character] request
 */
sealed class CharactersResult {

    class ValidResult(val result: List<Character>): CharactersResult()
    class EmptyResult : CharactersResult()
    class EmptyQuery : CharactersResult()
    class ErrorREsult(val e: Throwable) : CharactersResult()

}