package kz.mihael3d.rickandmortycharacters.data.locations.remote

import kz.mihael3d.rickandmortycharacters.data.model.PageResponse
import kz.mihael3d.rickandmortycharacters.data.locations.remote.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Location api of rickandmortyapi.com
 */
interface LocationsApi {

    @GET("location/")
    suspend fun getAllLocations(
        @Query("page") page: Int
    ): Response<PageResponse<LocationDto>>
}