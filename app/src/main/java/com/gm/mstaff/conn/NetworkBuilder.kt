package com.gm.mstaff.conn

import com.gm.mstaff.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by @godman on 21/06/23.
 */

object NetworkBuilder {
    val api : ApiServices by lazy {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor {
                    chain ->
                chain.proceed(chain.request().newBuilder().build())
            }.also { client ->
                if(BuildConfig.DEBUG){
                    val logging= HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()

        Retrofit.Builder()
            .baseUrl("https://private000.000webhostapp.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)
    }
}