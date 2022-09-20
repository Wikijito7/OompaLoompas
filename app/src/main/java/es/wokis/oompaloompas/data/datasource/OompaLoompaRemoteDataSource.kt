package es.wokis.oompaloompas.data.datasource

import es.wokis.oompaloompas.data.bo.OompaLoompaBO

interface OompaLoompaRemoteDataSource {
    suspend fun getOompaLoompas(): List<OompaLoompaBO>
    suspend fun getOompaLoompaById(id: Long): OompaLoompaBO
}