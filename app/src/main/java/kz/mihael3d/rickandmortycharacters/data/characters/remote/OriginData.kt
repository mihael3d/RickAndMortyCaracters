package kz.mihael3d.rickandmortycharacters.data.characters.remote


import com.google.gson.annotations.SerializedName



data class OriginData(
    @SerializedName("name")
    val name: String = "",

    @SerializedName("url")
    val url: String = ""
)