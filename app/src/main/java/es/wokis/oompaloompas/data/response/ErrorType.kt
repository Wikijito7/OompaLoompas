package es.wokis.oompaloompas.data.response

sealed class ErrorType(val debugMessage: String) {
    class NoConnectionError(debugMessage: String): ErrorType(debugMessage)
    class ServerError(debugMessage: String): ErrorType(debugMessage)
    class DataParseError(debugMessage: String): ErrorType(debugMessage)
    class UnknownError(debugMessage: String): ErrorType(debugMessage)
}