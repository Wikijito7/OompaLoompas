package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.repository.OompaLoompaRepository

interface GetMaxPageUseCase {
    operator fun invoke(): Int
}

class GetMaxPageUseCaseImpl(private val repository: OompaLoompaRepository) : GetMaxPageUseCase {

    override fun invoke(): Int = repository.getMaxPage()

}