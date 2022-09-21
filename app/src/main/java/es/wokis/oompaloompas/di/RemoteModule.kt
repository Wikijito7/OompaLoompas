package es.wokis.oompaloompas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.remote.oompaloompa.OompaLoompaRemoteDataSourceImpl
import es.wokis.oompaloompas.remote.oompaloompa.service.OompaLoompaService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideOompaLoompasRemoteDataSource(service: OompaLoompaService): OompaLoompaRemoteDataSource =
        OompaLoompaRemoteDataSourceImpl(service)
}