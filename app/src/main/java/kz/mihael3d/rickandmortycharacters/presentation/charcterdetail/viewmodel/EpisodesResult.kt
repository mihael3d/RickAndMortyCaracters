package kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.viewmodel

import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode

/**
 * *Class containing the result of the [Episode] request
 */
sealed class EpisodesResult {
    class ValidResult(val result:List<Episode>) : EpisodesResult()
    object EmptyResult : EpisodesResult()
    object EmptyQuery : EpisodesResult()
    class ErrorResult(val e: Throwable ) : EpisodesResult()
}