package com.armandorochin.borderwaittimes.di

import com.armandorochin.borderwaittimes.data.remote.bwt.BwtApi
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://bwt.cbp.gov/"

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBwtApi(retrofit: Retrofit): BwtApi {
        return retrofit.create(BwtApi::class.java)
    }
}