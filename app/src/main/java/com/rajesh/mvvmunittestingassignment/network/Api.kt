package com.rajesh.mvvmunittestingassignment.network

import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

/*
*Created by rajeshkumar07 on 01-08-2020
*/
interface Api {
    @GET("api/?results=20")
    fun getRandomUserList(): Single<ModelResponse>
}