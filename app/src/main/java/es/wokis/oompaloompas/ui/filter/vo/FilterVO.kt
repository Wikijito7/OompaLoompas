package es.wokis.oompaloompas.ui.filter.vo

data class FilterVO(
    val name: String,
    val type: FilterType,
    val selected: Boolean
)

enum class FilterType(val key: String) {
    PROFESSION("PROFESSION"),
    GENDER("GENDER")
}
