package com.rajesh.mvvmunittestingassignment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajesh.mvvmunittestingassignment.R
import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import com.rajesh.mvvmunittestingassignment.util.getProgressDrawable
import com.rajesh.mvvmunittestingassignment.util.loadImage
import com.rajesh.mvvmunittestingassignment.view.DetailsActivity
import kotlinx.android.synthetic.main.row_item.view.*

/*
*Created by rajeshkumar07 on 01-08-2020
*/
class MainAdapter(var userList: ArrayList<ModelResponse.Result>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    fun updateUserList(newUserList: List<ModelResponse.Result>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.row_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(v?.context, DetailsActivity::class.java)
                intent.putExtra("USER_DETAILS",userList[position])
                v?.context?.startActivity(intent)
            }
        })
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.tvName
        private val email = view.tvEmail
        private val cell = view.tvCell
        private val gender = view.tvGender
        private val image = view.imageView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(response: ModelResponse.Result) {
            val nameText = "Name: " + response.name.first
            name.text = nameText
            val emailText = "Email: " + response.email
            email.text = emailText
            val cellText = "Cell: " + response.cell
            cell.text = cellText
            val genderText = "Gender: " + response.gender
            gender.text = genderText
            image.loadImage(response.picture.thumbnail, progressDrawable)
        }

    }
}