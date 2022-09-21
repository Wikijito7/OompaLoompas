package es.wokis.oompaloompas.data.response

sealed class AsyncResult<T>(val data: T?, val debugMessage: String?) {
    class Success<T>(data: T?) : AsyncResult<T>(data, null)
    class Error<T>(val error: ErrorType) : AsyncResult<T>(null, error.debugMessage)
}


