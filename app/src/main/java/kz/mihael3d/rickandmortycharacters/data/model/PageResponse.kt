package kz.mihael3d.rickandmortycharacters.data.model

import com.google.gson.annotations.SerializedName

data class PageResponse<T>(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<T> = listOf()
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)