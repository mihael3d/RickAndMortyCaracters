package kz.mihael3d.rickandmortycharacters.data.api

import kz.mihael3d.rickandmortycharacters.data.model.Character
import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PageResponse<Character>>


}