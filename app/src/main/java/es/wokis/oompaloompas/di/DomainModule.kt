package es.wokis.oompaloompas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.wokis.oompaloompas.data.repository.OompaLoompaRepository
import es.wokis.oompaloompas.domain.*

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetOompaLoompasUseCase(repository: OompaLoompaRepository): GetOompaLoompasUseCase =
        GetOompaLoompasUseCaseImpl(repository)

    @Provides
    fun provideGetOompaLoompaByIdUseCase(repository: OompaLoompaRepository): GetOompaLoompaByIdUseCase =
        GetOompaLoompaByIdUseCaseImpl(repository)

    @Provides
    fun provideGetMaxPageUseCase(repository: OompaLoompaRepository): GetMaxPageUseCase =
        GetMaxPageUseCaseImpl(repository)
}