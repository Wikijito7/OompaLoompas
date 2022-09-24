package es.wokis.oompaloompas.data.remote.oompaloompa.mapper

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.bo.OompaLoompaFavoritesBO
import es.wokis.oompaloompas.data.bo.OompaLoompaResponseBO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaFavoritesDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaResponseDTO
import es.wokis.oompaloompas.utils.orZero

fun OompaLoompaDTO.toBO() = OompaLoompaBO(
    id = id,
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    description = description,
    quota = quota,
    age = age.orZero(),
    gender = gender.orEmpty(),
    image = image.orEmpty(),
    profession = profession.orEmpty(),
    email = email.orEmpty(),
    country = country.orEmpty(),
    height = height.orZero(),
    favorites = favorites.toBO()
)

fun List<OompaLoompaDTO>?.toBO() = this?.map { it.toBO() }.orEmpty()

fun OompaLoompaFavoritesDTO?.toBO() = this?.let {
    OompaLoompaFavoritesBO(
        color = color,
        food = food,
        randomString = randomString,
        song = song
    )
}

fun OompaLoompaResponseDTO.toBO() = OompaLoompaResponseBO(
    current = current.orZero(),
    total = total.orZero(),
    results = results.toBO()
)