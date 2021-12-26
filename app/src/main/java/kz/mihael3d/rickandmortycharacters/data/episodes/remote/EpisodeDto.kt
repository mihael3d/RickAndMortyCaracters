package kz.mihael3d.rickandmortycharacters.data.episodes.remote

import com.google.gson.annotations.SerializedName


/**
 * Class of Episode coming from the api
 */
class EpisodeDto(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("air_date")
    val airDate: String = "",

    @SerializedName("code", alternate = ["episode"])
    val episode: String = "",

    @SerializedName("residents")
    val characters: List<String> = emptyList(),

    @SerializedName("url")
    val url: String = "",

    @SerializedName("created")
    val created: String = ""
)