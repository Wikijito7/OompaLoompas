package es.wokis.oompaloompas.domain

import es.wokis.oompaloompas.data.repository.OompaLoompaRepository

interface GetProfessionsUseCase {
    operator fun invoke(): List<String>
}

class GetProfessionsUseCaseImpl(private val repository: OompaLoompaRepository) : GetProfessionsUseCase {

    override fun invoke(): List<String> = repository.getProfessions()

}