package com.rajesh.mvvmunittestingassignment.network

import com.rajesh.mvvmunittestingassignment.di.component.DaggerApiComponent
import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import io.reactivex.Single
import javax.inject.Inject

/*
*Created by rajeshkumar07 on 01-08-2020
*/
class UserService {

    @Inject
    lateinit var api: Api

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getUser(): Single<ModelResponse> {
        return api.getRandomUserList()
    }
}