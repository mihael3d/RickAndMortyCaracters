package kz.mihael3d.rickandmortycharacters.data.api

import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApi {

    @GET("location/")
    suspend fun getAllLocations(@Query("page") page: Int): Response<PageResponse<Location>>
}