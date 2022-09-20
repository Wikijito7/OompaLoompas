package es.wokis.oompaloompas.remote.oompaloompa

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.remote.oompaloompa.service.OompaLoompaService

class OompaLoompaRemoteDataSourceImpl(private val service: OompaLoompaService) : OompaLoompaRemoteDataSource {
    override suspend fun getOompaLoompas(): List<OompaLoompaBO> {
        TODO("Not yet implemented")
    }

    override suspend fun getOompaLoompaById(id: Long): OompaLoompaBO {
        TODO("Not yet implemented")
    }
}