package com.chimemoo.alwayshungry.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.chimemoo.alwayshungry.R
import com.chimemoo.alwayshungry.data.api.ApiHelper
import com.chimemoo.alwayshungry.data.api.ApiService
import com.chimemoo.alwayshungry.ui.base.ViewModelFactory
import com.chimemoo.alwayshungry.ui.main.adapter.MainAdapter
import com.chimemoo.alwayshungry.ui.main.viewmodel.MainViewModel
import com.chimemoo.alwayshungry.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObserver()
        setupAction()
    }

    private fun setupObserver() {
        imageObserve()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiService()))
        ).get(MainViewModel::class.java)
    }

    private fun setupAction() {
        btn_refresh.setOnClickListener {
            mainViewModel.getRefreshedImage()
        }
    }

    private fun imageObserve() {
        mainViewModel.getImage().observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    iv_food.visibility = View.VISIBLE
                    pb_image.visibility = View.GONE
                    Glide.with(this)
                        .load(it.data?.image)
                        .into(iv_food)
                }
                Status.LOADING -> {
                    pb_image.visibility = View.VISIBLE
                    iv_food.visibility = View.GONE
                }
                Status.ERROR -> {
                    pb_image.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}