package kz.mihael3d.rickandmortycharacters.data.characters.remote

import com.google.gson.annotations.SerializedName
import kz.mihael3d.rickandmortycharacters.data.characters.Gender
import kz.mihael3d.rickandmortycharacters.data.model.Status

/**
 * Class of Character coming from the api
 */
class CharacterDto(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("status")
    val status: Status = Status.UNKNOWN,

    @SerializedName("species")
    val species: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("gender")
    val gender: Gender = Gender.UNKNOWN,

    @SerializedName("image")
    val image: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("origin")
    val origin: OriginData = OriginData(),

    @SerializedName("location")
    val location: LocationData = LocationData(),

    @SerializedName("episode")
    val episode: List<String> = emptyList(),

    var page: Int?
)  {
}