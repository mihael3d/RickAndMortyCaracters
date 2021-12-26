package kz.mihael3d.rickandmortycharacters.presentation

sealed class SearchState
object Loading : SearchState()
object Ready : SearchState()