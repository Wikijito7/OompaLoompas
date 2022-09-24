package es.wokis.oompaloompas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.wokis.oompaloompas.data.datasource.OompaLoompaRemoteDataSource
import es.wokis.oompaloompas.data.remote.oompaloompa.OompaLoompaRemoteDataSourceImpl
import es.wokis.oompaloompas.data.remote.oompaloompa.service.OompaLoompaService

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideOompaLoompasRemoteDataSource(service: OompaLoompaService): OompaLoompaRemoteDataSource =
        OompaLoompaRemoteDataSourceImpl(service)
}