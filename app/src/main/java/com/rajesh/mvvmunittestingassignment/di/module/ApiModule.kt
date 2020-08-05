package com.rajesh.mvvmunittestingassignment.di.module

import com.rajesh.mvvmunittestingassignment.network.Api
import com.rajesh.mvvmunittestingassignment.network.RetrofitService
import com.rajesh.mvvmunittestingassignment.network.UserService
import com.rajesh.mvvmunittestingassignment.util.Utils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
*Created by rajeshkumar07 on 01-08-2020
*/
@Module
class ApiModule {

    @Provides
    fun provideUserApi(): Api {
        return RetrofitService.create()!!
    }

    @Provides
    fun provideUserService() : UserService{
        return UserService()
    }
}