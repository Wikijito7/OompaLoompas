package es.wokis.oompaloompas.data.error

import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.ErrorType
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object RepositoryErrorManager {
    suspend fun <T> wrap(block: suspend () -> T): AsyncResult<T> {
        return try {
            AsyncResult.Success(block())

        } catch (exc: Exception) {
            manageError(exc)
        }
    }

    private fun <T> manageError(exc: Exception): AsyncResult.Error<T> = AsyncResult.Error(
        when (exc.cause) {
            is IOException -> ErrorType.ServerError("Server error")
            is SocketTimeoutException, is UnknownHostException, is ConnectException -> ErrorType.NoConnectionError(
                "No connection available"
            )
            is IllegalFormatException -> ErrorType.DataParseError("Error parsing data")
            else -> ErrorType.UnknownError("Unknown error")
        }
    )
}