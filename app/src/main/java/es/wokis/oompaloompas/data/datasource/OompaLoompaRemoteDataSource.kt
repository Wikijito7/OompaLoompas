package es.wokis.oompaloompas.data.datasource

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.bo.OompaLoompaResponseBO

interface OompaLoompaRemoteDataSource {
    suspend fun getOompaLoompas(page: Int): OompaLoompaResponseBO
    suspend fun getOompaLoompaById(id: Long): OompaLoompaBO
}