package com.github.formatc00.di.module

import com.github.formatc00.BuildConfig
import com.github.formatc00.data.network.CryptoApi
import com.github.formatc00.util.Logger
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    
    private val logger = Logger.create(this)
    
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger(logger::d))
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }
    
    @Provides
    fun provideCertificatePinner() = CertificatePinner.Builder().build()
    
    @Provides
    @Singleton
    fun provideDefaultOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        pinner: CertificatePinner
    ) = OkHttpClient().newBuilder()
        .certificatePinner(pinner)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .build()
    
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    
    @Provides
    @Singleton
    fun provideAuthorizedApi(retrofit: Retrofit) = retrofit.create(CryptoApi::class.java)
    
    companion object {
        
        const val TIMEOUT = 30
    }
}