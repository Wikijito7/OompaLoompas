package es.wokis.oompaloompas.ui.oompa.mapper

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO

fun OompaLoompaBO.toVO() = OompaLoompaVO(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    age = age,
    profession = profession
)