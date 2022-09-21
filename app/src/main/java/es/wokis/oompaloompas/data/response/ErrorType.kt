package es.wokis.oompaloompas.data.response

sealed class ErrorType(val errorMessage: String) {
    class NoConnectionError(errorMessage: String): ErrorType(errorMessage)
    class ServerError(errorMessage: String): ErrorType(errorMessage)
    class DataParseError(errorMessage: String): ErrorType(errorMessage)
    class UnknownError(errorMessage: String): ErrorType(errorMessage)
}