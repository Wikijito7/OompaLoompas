package es.wokis.oompaloompas.remote.oompaloompa.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaService {
    @GET("/napptilus/oompa-loompas?page=1")
    suspend fun getOompaLoompas(@Query("page") pageNumber: Int)

    @GET("napptilus/oompa-loompas/{id}")
    suspend fun getOompaLoompaById(@Path("id") id: Long)
}