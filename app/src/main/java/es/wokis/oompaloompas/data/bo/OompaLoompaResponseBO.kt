package es.wokis.oompaloompas.data.bo

data class OompaLoompaResponseBO(
    val current: Int,
    val total: Int,
    val results: List<OompaLoompaBO>
)