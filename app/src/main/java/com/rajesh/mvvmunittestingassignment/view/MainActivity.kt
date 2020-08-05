package com.rajesh.mvvmunittestingassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rajesh.mvvmunittestingassignment.R
import com.rajesh.mvvmunittestingassignment.adapter.MainAdapter
import com.rajesh.mvvmunittestingassignment.model.ModelResponse
import com.rajesh.mvvmunittestingassignment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: MainViewModel
    private var mainAdapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instantiateTheViewModel()
    }

    /**
     * initialize viewmodel class and fetch the remote data.
     */
    fun instantiateTheViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.refresh()

        userList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        //set the refresh listener
        swipeRefreshLayout.setOnRefreshListener(this)

        //observe the view model if there are changes
        observeViewModel()

    }

    /**
     * ObserveModel method listens for changes on fetched data
     *  if there is an error
     *  @property UserLoadError is called to show error message
     *  @property Users is called when data is succesfully observed and loaded from remote or local
     *  @property loading defines whether to show loading spinner or hide it
     * */
    fun observeViewModel() {
        viewModel.users.observe(this, Observer { users ->
            users?.let { list ->
                userList.visibility = View.VISIBLE
                mainAdapter.updateUserList(list.results)
            }
        })

        viewModel.userLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    list_error.visibility = View.GONE
                    userList.visibility = View.GONE
                }
            }
        })
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = false
        viewModel.refresh()
    }
}
