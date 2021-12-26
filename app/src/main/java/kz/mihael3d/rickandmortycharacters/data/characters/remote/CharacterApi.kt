package kz.mihael3d.rickandmortycharacters.data.characters.remote

import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Charactes api of rickandmortyapi.com
 */
interface CharacterApi {

    @GET("character/")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<PageResponse<CharacterDto>>

    @GET("character/")
    suspend fun searchCharacterByName(
        @Query("name") name: String,
        @Query("page") page: Int
    ): Response<PageResponse<CharacterDto>>

    @GET("character/")
    suspend fun searchCharacter(
        @Query("page") page: Int
    ): Response<PageResponse<CharacterDto>>
}
