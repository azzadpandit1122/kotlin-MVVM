package com.demo.retrofithttpmethods

import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetroInstance {
    companion object {

        var gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }




        private fun getOkHttpClient(): OkHttpClient {


            val builder: OkHttpClient.Builder = OkHttpClient.Builder().retryOnConnectionFailure(true)
            //Print Log
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
            builder.connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            return builder.readTimeout(15, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)
                .build()


        }

        val service: ApiRequest by lazy {
            retrofit.create(ApiRequest::class.java)
        }
    }


}