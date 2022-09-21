package es.wokis.oompaloompas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.wokis.oompaloompas.data.constants.WSConstants
import es.wokis.oompaloompas.remote.oompaloompa.service.OompaLoompaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideOompaLoompaService(): OompaLoompaService =
        getAdapter(WSConstants.OOMPA_LOOMPAS_BASE_URL).create(OompaLoompaService::class.java)

    private fun getAdapter(baseUrl: String) = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}