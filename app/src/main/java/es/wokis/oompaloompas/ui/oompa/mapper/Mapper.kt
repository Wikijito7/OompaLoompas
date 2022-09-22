package es.wokis.oompaloompas.ui.oompa.mapper

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO

fun OompaLoompaBO.toVO() = OompaLoompaVO(
    id = id,
    firstName = firstName,
    lastName = lastName,
    image = image,
    email = email,
    age = age,
    profession = profession
)

fun List<OompaLoompaBO>.toVO() = this.map { it.toVO() }