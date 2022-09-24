package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.repository.OompaLoompaRepository

interface GetSavedFiltersUseCase {
    operator fun invoke(): Pair<String?, String?>
}

class GetSavedFiltersUseCaseImpl(private val repository: OompaLoompaRepository) : GetSavedFiltersUseCase {

    override fun invoke(): Pair<String?, String?> = repository.getSavedFilters()

}