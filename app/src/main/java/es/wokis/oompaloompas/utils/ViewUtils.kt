package es.wokis.oompaloompas.utils

import android.view.View

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.setVisible(condition: Boolean) {
    if (condition) {
        this.show()

    } else {
        this.hide()
    }
}