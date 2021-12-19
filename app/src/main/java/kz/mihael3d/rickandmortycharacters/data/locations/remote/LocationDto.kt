package kz.mihael3d.rickandmortycharacters.data.locations.remote

import com.google.gson.annotations.SerializedName


/**
 * Class of Location coming from the api
 */
data class LocationDto(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("dimension")
    val dimension: String = "",

    @SerializedName("residents")
    val residents: List<String> = emptyList(),

    @SerializedName("created")
    val created: String = ""
)