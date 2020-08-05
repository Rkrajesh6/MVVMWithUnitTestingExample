package com.rajesh.mvvmunittestingassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rajesh.mvvmunittestingassignment.R
import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import com.rajesh.mvvmunittestingassignment.util.getProgressDrawable
import com.rajesh.mvvmunittestingassignment.util.loadImage
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val modelResult = intent.getSerializableExtra("USER_DETAILS") as ModelResponse.Result
        Log.e("TAG",modelResult.cell)
        val progressDrawable = getProgressDrawable(this)
        imgProfile.loadImage(modelResult.picture.large,progressDrawable = progressDrawable)
        val fullName = "Name : "+modelResult.name.title+" "+modelResult.name.first +" "+modelResult.name.last
        tvName.text = fullName
        val email = "Email : "+modelResult.email
        tvEmail.text = email
        val gender = "Gender : "+modelResult.gender
        tvGender.text = gender
        val cell = "Cell : "+modelResult.cell
        tvCell.text = cell
        val dob = "DOB : "+modelResult.dob.date
        tvDob.text = dob
        val phone = "Phone : "+modelResult.phone
        tvPhone.text = phone
        val location = "Location : "+modelResult.location.city
        tvLocation.text = location

    }
}
