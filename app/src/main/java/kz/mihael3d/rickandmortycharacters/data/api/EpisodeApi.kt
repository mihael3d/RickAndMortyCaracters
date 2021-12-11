package kz.mihael3d.rickandmortycharacters.data.api

import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
    @GET("episode/")
    suspend fun getAllEpisodes(@Query("page") page: Int): Response<PageResponse<Episode>>
}