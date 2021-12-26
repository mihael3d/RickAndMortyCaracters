package kz.mihael3d.rickandmortycharacters.domain.episodes.models
/**
 * Business class of Episode
 */
data class Episode(
    val id: Int = 0,
    val name: String = "",
    val airDate: String = "",
    val code: String = "",
    val characters: List<String> = emptyList(),
    val created: String = ""
)