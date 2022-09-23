package es.wokis.oompaloompas.utils

fun String?.capitalize() = this?.let {
    it.firstOrNull()?.uppercase() + it.substring(1)
}.orEmpty()