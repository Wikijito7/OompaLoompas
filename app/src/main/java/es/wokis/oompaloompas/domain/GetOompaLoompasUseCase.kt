package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.repository.OompaLoompaRepository
import es.wokis.oompaloompas.data.response.AsyncResult
import kotlinx.coroutines.flow.Flow

interface GetOompaLoompasUseCase {
    suspend operator fun invoke(page: Int): Flow<AsyncResult<List<OompaLoompaBO>>>
}

class GetOompaLoompasUseCaseImpl(private val repository: OompaLoompaRepository) :
    GetOompaLoompasUseCase {
    override suspend fun invoke(page: Int):  Flow<AsyncResult<List<OompaLoompaBO>>> =
        repository.getOompaLoompas(page)
}