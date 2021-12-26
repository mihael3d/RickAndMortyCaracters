package kz.mihael3d.rickandmortycharacters.data.episodes.remote

import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {
    @GET("episode/")
    suspend fun getAllEpisodes(
        @Query("page") page: Int =1
    ): Response<PageResponse<EpisodeDto>>


    @GET("episode/{episode_id}")
    suspend fun getEpisodesById(
        @Path("episode_id") id: String
    ): List<EpisodeDto>

    @GET("episode/{episode_id}")
    suspend fun getEpisodeById(
        @Path("episode_id") id: Int
    ): EpisodeDto
}