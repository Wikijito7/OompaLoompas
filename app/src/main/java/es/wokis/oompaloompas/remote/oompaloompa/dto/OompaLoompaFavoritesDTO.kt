package es.wokis.oompaloompas.remote.oompaloompa.dto

import com.google.gson.annotations.SerializedName

data class OompaLoompaFavoritesDTO(
    @SerializedName("color")
    val color: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("random_string")
    val randomString: String,
    @SerializedName("song")
    val song: String
)