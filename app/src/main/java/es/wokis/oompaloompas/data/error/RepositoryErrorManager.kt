package es.wokis.oompaloompas.data.error

import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.ErrorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.CancellationException

object RepositoryErrorManager {
    suspend fun <T> wrap(block: suspend () -> T): Flow<AsyncResult<T>> = flow {
        emit(AsyncResult.Loading())
        try {
            emit(AsyncResult.Success(block()))

        } catch (exc: CancellationException) {
            // If is a CancellationException, we throw it as it's necessary for the framework
            // in order to remove the coroutine.
            throw exc

        } catch (exc: Exception) {
            emit(manageError(exc))
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