package es.wokis.oompaloompas.remote.oompaloompa

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.remote.oompaloompa.service.OompaLoompaService

class OompaLoompaRemoteDataSourceImpl(private val service: OompaLoompaService) :
    OompaLoompaRemoteDataSource {

    override suspend fun getOompaLoompas(page: Int): List<OompaLoompaBO> =
        service.getOompaLoompas(page)

    override suspend fun getOompaLoompaById(id: Long): OompaLoompaBO =
        service.getOompaLoompaById(id)

}