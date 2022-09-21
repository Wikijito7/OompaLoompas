package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.repository.OompaLoompaRepository
import es.wokis.oompaloompas.data.response.AsyncResult

interface GetOompaLoompasUseCase {
    suspend operator fun invoke(page: Int): AsyncResult<List<OompaLoompaBO>>
}

class GetOompaLoompasUseCaseImpl(private val repository: OompaLoompaRepository) :
    GetOompaLoompasUseCase {
    override suspend fun invoke(page: Int): AsyncResult<List<OompaLoompaBO>> =
        repository.getOompaLoompas(page)
}