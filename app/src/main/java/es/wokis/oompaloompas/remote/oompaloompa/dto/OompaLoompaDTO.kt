package es.wokis.oompaloompas.remote.oompaloompa.dto

class OompaLoompaDTO(
    val id: Long,
    val firstName: String,
    val secondName: String,
    val description: String?,
    val quota: String?,
    val favorites: OompaLoompaFavoritesDTO,
    val gender: String,
    val image: String,
    val profession: String,
    val email: String,
    val age: Int,
    val country: String,
    val height: Int,
)