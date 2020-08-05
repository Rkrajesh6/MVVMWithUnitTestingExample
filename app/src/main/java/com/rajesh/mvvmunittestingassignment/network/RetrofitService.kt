package com.rajesh.mvvmunittestingassignment.network

import com.rajesh.mvvmunittestingassignment.util.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
*Created by rajeshkumar07 on 01-08-2020
*/
object RetrofitService {

    private var mApiInterface: Api? = null

    fun create(): Api? {
        val builder = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(Utils.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(Utils.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor())

        val retrofit = builder.client(httpClient.build()).build()

        mApiInterface = retrofit.create(Api::class.java)
        return mApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}

