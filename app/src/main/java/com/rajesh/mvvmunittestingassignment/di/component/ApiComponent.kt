package com.rajesh.mvvmunittestingassignment.di.component

import com.rajesh.mvvmunittestingassignment.di.module.ApiModule
import com.rajesh.mvvmunittestingassignment.network.UserService
import com.rajesh.mvvmunittestingassignment.repository.MainRepository
import com.rajesh.mvvmunittestingassignment.viewmodel.MainViewModel
import dagger.Component

/*
*Created by rajeshkumar07 on 01-08-2020
*/
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service : UserService)

    fun inject(mainRepository : MainRepository)

}