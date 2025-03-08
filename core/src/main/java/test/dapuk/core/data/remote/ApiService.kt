package test.dapuk.core.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("creators")
    suspend fun getDevelopersList(
        @Query("key") key: String
    ) : Response<GameResponse>

    @GET("creators/{id}")
    suspend fun getDevelopersDetails(
        @Path("id") id: String,
        @Query("key") key: String
    ) : Response<Detail>
}