package es.wokis.oompaloompas.data.repository

import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.error.RepositoryErrorManager
import es.wokis.oompaloompas.data.response.AsyncResult
import kotlinx.coroutines.flow.Flow

interface OompaLoompaRepository {
    suspend fun getOompaLoompas(page: Int): Flow<AsyncResult<List<OompaLoompaBO>>>
    suspend fun getOompaLoompaById(id: Long): Flow<AsyncResult<OompaLoompaBO>>
    fun getMaxPage(): Int
    fun getProfessions(): List<String>
    fun getSavedFilters() : Pair<String?, String?>
    fun saveFilters(filters: Pair<String?, String?>): Boolean
}

class OompaLoompaRepositoryImpl(private val remoteDataSource: OompaLoompaRemoteDataSource) :
    OompaLoompaRepository {

    private var maxPage = 0
    private var professions: MutableList<String> = mutableListOf()
    private var savedFilters: Pair<String?, String?> = Pair(null, null)

    override suspend fun getOompaLoompas(page: Int): Flow<AsyncResult<List<OompaLoompaBO>>> =
        RepositoryErrorManager.wrap {
            val oompaLoompasResponse = remoteDataSource.getOompaLoompas(page)
            maxPage = oompaLoompasResponse.total
            professions.addAll(oompaLoompasResponse.results.map { it.profession }.distinct())
            professions = professions.distinct().toMutableList()
            oompaLoompasResponse.results
        }

    override suspend fun getOompaLoompaById(id: Long): Flow<AsyncResult<OompaLoompaBO>> =
        RepositoryErrorManager.wrap {
            remoteDataSource.getOompaLoompaById(id)
        }

    override fun getMaxPage(): Int = maxPage

    override fun getProfessions(): List<String> = professions.toList()

    override fun getSavedFilters(): Pair<String?, String?> = savedFilters

    override fun saveFilters(filters: Pair<String?, String?>): Boolean {
        this.savedFilters = filters
        return true
    }

}