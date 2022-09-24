package es.wokis.oompaloompas.data.remote.oompaloompa.service

import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaService {
    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompas(@Query("page") pageNumber: Int): OompaLoompaResponseDTO

    @GET("/napptilus/oompa-loompas/{id}")
    suspend fun getOompaLoompaById(@Path("id") id: Long): OompaLoompaDTO
}