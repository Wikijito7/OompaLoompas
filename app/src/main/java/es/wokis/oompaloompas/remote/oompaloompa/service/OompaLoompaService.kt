package es.wokis.oompaloompas.remote.oompaloompa.service

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaService {
    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompas(@Query("page") pageNumber: Int): List<OompaLoompaBO>

    @GET("/napptilus/oompa-loompas/{id}")
    suspend fun getOompaLoompaById(@Path("id") id: Long): OompaLoompaBO
}