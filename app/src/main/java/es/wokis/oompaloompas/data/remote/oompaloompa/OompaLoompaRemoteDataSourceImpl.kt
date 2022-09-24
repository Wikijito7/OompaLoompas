package es.wokis.oompaloompas.data.remote.oompaloompa

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.bo.OompaLoompaResponseBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.remote.oompaloompa.mapper.toBO
import es.wokis.oompaloompas.data.remote.oompaloompa.service.OompaLoompaService

class OompaLoompaRemoteDataSourceImpl(private val service: OompaLoompaService) :
    OompaLoompaRemoteDataSource {

    override suspend fun getOompaLoompas(page: Int): OompaLoompaResponseBO =
        service.getOompaLoompas(page).toBO()

    override suspend fun getOompaLoompaById(id: Long): OompaLoompaBO =
        service.getOompaLoompaById(id).toBO()

}