package com.homework.crudfan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.crudfan.databinding.ActivityListDataBinding

class ListData : AppCompatActivity() {

    private lateinit var activityListDataBinding: ActivityListDataBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityListDataBinding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(activityListDataBinding.root)

        listAdapter = ListAdapter()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DataViewModel::class.java]

        showData(this)
    }

    private fun showData(context: Context) {
        viewModel.setData(context)
        viewModel.getData().observe(this, { data ->
            if (data != null) {
                activityListDataBinding.ivIcon.visibility = View.GONE
                listAdapter.setData(data)

                with(activityListDataBinding.rvList) {
                    layoutManager = LinearLayoutManager(this@ListData)
                    setHasFixedSize(true)
                    adapter = listAdapter
                }
            }
        })
    }
}