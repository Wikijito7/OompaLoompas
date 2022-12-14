package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.repository.OompaLoompaRepository

interface SaveFiltersUseCase {
    operator fun invoke(filters: Pair<String?, String?>): Boolean
}

class SaveFiltersUseCaseImpl(private val repository: OompaLoompaRepository) : SaveFiltersUseCase {

    override fun invoke(filters: Pair<String?, String?>): Boolean = repository.saveFilters(filters)

}