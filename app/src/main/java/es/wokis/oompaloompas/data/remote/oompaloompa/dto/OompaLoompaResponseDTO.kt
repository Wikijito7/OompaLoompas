package es.wokis.oompaloompas.data.remote.oompaloompa.dto

import com.google.gson.annotations.SerializedName

data class OompaLoompaResponseDTO(
    @SerializedName("current")
    val current: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("results")
    val results: List<OompaLoompaDTO>?
)