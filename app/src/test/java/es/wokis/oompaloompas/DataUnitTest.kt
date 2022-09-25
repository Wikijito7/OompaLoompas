package es.wokis.oompaloompas

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.bo.OompaLoompaFavoritesBO
import es.wokis.oompaloompas.data.bo.OompaLoompaResponseBO
import es.wokis.oompaloompas.data.error.RepositoryErrorManager
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaFavoritesDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.dto.OompaLoompaResponseDTO
import es.wokis.oompaloompas.data.remote.oompaloompa.mapper.toBO
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.ErrorType
import es.wokis.oompaloompas.data.response.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.ConnectException

class DataUnitTest {
    @Test
    fun `assert repository error manager wrapper returns success`() {
        runBlocking {
            val asyncResult = RepositoryErrorManager.wrap {
                listOf<String>()
            }.last()

            assert(asyncResult is AsyncResult.Success)
        }
    }

    @Test
    fun `assert repository error manager wrapper returns error`() {
        runBlocking {
            val asyncResult = RepositoryErrorManager.wrap {
                throw ConnectException()
            }.last()

            assert(asyncResult is AsyncResult.Error)
            assert((asyncResult as? AsyncResult.Error)?.error is ErrorType.NoConnectionError)
        }
    }

    @Test
    fun `assert repository error manager wrapper returns loading`() {
        runBlocking {
            val asyncResult = RepositoryErrorManager.wrap {
                throw ConnectException()
            }.first()

            assert(asyncResult is AsyncResult.Loading)
        }
    }

    @Test
    fun `assert async result mapper maps types`() {
        val asyncResult: AsyncResult<Int> = AsyncResult.Success(1)
        assert(asyncResult.map { it.toLong() } is AsyncResult<Long>)
    }

    @Test
    fun `assert oompa loompa dto mapper maps to bo`() {
        val oompaLoompaDTO = OompaLoompaDTO(
            id = 1,
            firstName = "Cris",
            lastName = "Kat",
            description = "I love cats!",
            quota = "A Cat is nice, two are better!",
            age = 22,
            gender = "M",
            image = null,
            profession = "Developer",
            email = "me@somewhere.com",
            country = "Spain",
            height = 177,
            favorites = null
        )
        assert(oompaLoompaDTO.toBO() is OompaLoompaBO)
    }

    @Test
    fun `assert favorites dto mapper maps to bo`() {
        val favoritesDTO = OompaLoompaFavoritesDTO(
            color = "Green",
            food = "Pizza",
            randomString = "lorem ipsum...",
            song = "Never gonna give you up"
        )
        assert(favoritesDTO.toBO() is OompaLoompaFavoritesBO)
    }

    @Test
    fun `assert oompa response dto mapper maps to bo`() {
        val oompaLoompaDTO = listOf(
            OompaLoompaDTO(
                id = 1,
                firstName = "Cris",
                lastName = "Kat",
                description = "I love cats!",
                quota = "A Cat is nice, two are better!",
                age = 22,
                gender = "M",
                image = null,
                profession = "Developer",
                email = "me@somewhere.com",
                country = "Spain",
                height = 177,
                favorites = null
            )
        )
        val oompaResponseDTO = OompaLoompaResponseDTO(
            current = 1,
            total = 20,
            results = oompaLoompaDTO
        )
        val oompaResponse = oompaResponseDTO.toBO()
        assert(oompaResponse is OompaLoompaResponseBO)
        assert(oompaResponse.results.isNotEmpty())
    }
}