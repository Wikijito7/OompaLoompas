package es.wokis.oompaloompas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.repository.OompaLoompaRepository
import es.wokis.oompaloompas.data.repository.OompaLoompaRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideOompaLoompasRepository(remoteDataSource: OompaLoompaRemoteDataSource): OompaLoompaRepository =
        OompaLoompaRepositoryImpl(remoteDataSource)
}