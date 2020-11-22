package com.swap.spacex.di.module

import com.google.gson.GsonBuilder
import com.swap.spacex.BuildConfig.BASE_URL
import com.swap.spacex.api.SpaceXApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Module which provides all required dependencies for networking
 */
@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSpaceXApi(retrofit: Retrofit): SpaceXApi =
        retrofit.create(SpaceXApi::class.java)

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request = chain.request().newBuilder().apply {

                }.build()
                chain.proceed(request)
            }

//            if (DEBUG) {
//                add interceptor with Level BODY
//            }

            followRedirects(true)
            followSslRedirects(true)
            retryOnConnectionFailure(true)
            cache(null).connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
        }
            //add certificate Pinner
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }
}