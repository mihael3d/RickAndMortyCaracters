package kz.mihael3d.rickandmortycharacters.domain.locations.models

import com.google.gson.annotations.SerializedName
/**
 * Business class of Location
 */
data class Location(
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val residents: List<String> = emptyList(),
    val created: String = ""
    )
