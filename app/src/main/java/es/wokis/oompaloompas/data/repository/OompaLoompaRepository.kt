package es.wokis.oompaloompas.data.repository

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.error.RepositoryErrorManager
import es.wokis.oompaloompas.data.response.AsyncResult
import kotlinx.coroutines.flow.Flow

interface OompaLoompaRepository {
    suspend fun getOompaLoompas(page: Int):  Flow<AsyncResult<List<OompaLoompaBO>>>
    suspend fun getOompaLoompaById(id: Long):  Flow<AsyncResult<OompaLoompaBO>>
    fun getMaxPage(): Int
}

class OompaLoompaRepositoryImpl(private val remoteDataSource: OompaLoompaRemoteDataSource) :
    OompaLoompaRepository {

    private var maxPage = 0

    override suspend fun getOompaLoompas(page: Int): Flow<AsyncResult<List<OompaLoompaBO>>> =
        RepositoryErrorManager.wrap {
            val oompaLoompasResponse = remoteDataSource.getOompaLoompas(page)
            maxPage = oompaLoompasResponse.total
            oompaLoompasResponse.results
        }

    override suspend fun getOompaLoompaById(id: Long):  Flow<AsyncResult<OompaLoompaBO>> = RepositoryErrorManager.wrap {
        remoteDataSource.getOompaLoompaById(id)
    }

    override fun getMaxPage(): Int = maxPage

}