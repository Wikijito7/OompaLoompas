package es.wokis.oompaloompas.data.bo

data class OompaLoompaBO(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val description: String?,
    val quota: String?,
    val favorites: OompaLoompaFavoritesBO?,
    val gender: String,
    val image: String,
    val profession: String,
    val email: String,
    val age: Int,
    val country: String,
    val height: Int,
)