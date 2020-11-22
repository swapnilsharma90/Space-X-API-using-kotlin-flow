package com.swap.spacex.api

import com.swap.spacex.BuildConfig.BASE_URL
import com.swap.spacex.features.feed.model.PastLaunchesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {

    //this method is to get all the past launches by using spaceX api

    //offset...0,10,20,30 and so on...
    @GET("launches/past/")
    suspend fun getAllPastLaunches(
        @Query("offset") page: Int,
        @Query("limit") size: Int = 10
    ): List<PastLaunchesResponse>

    companion object {

        operator fun invoke(): SpaceXApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceXApi::class.java)

        //cert pinner can be added to avoid MITM attacks.
    }

}