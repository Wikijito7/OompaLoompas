package es.wokis.oompaloompas.data.repository

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.error.RepositoryErrorManager
import es.wokis.oompaloompas.data.response.AsyncResult

interface OompaLoompaRepository {
    suspend fun getOompaLoompas(): AsyncResult<List<OompaLoompaBO>>
    suspend fun getOompaLoompaById(id: Long): AsyncResult<OompaLoompaBO>
}

class OompaLoompaRepositoryImpl(private val remoteDataSource: OompaLoompaRemoteDataSource) :
    OompaLoompaRepository {
    override suspend fun getOompaLoompas(): AsyncResult<List<OompaLoompaBO>> =
        RepositoryErrorManager.wrap {
            remoteDataSource.getOompaLoompas()
        }

    override suspend fun getOompaLoompaById(id: Long): AsyncResult<OompaLoompaBO> = RepositoryErrorManager.wrap {
        remoteDataSource.getOompaLoompaById(id)
    }

}