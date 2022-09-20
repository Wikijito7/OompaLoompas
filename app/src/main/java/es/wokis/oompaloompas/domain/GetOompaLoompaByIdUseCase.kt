package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.repository.OompaLoompaRepository
import es.wokis.oompaloompas.data.response.AsyncResult

interface GetOompaLoompaByIdUseCase {
    suspend operator fun invoke(id: Long): AsyncResult<OompaLoompaBO>
}

class GetOompaLoompaByIdUseCaseImpl(private val repository: OompaLoompaRepository) : GetOompaLoompaByIdUseCase {
    override suspend fun invoke(id: Long): AsyncResult<OompaLoompaBO> = repository.getOompaLoompaById(id)
}

