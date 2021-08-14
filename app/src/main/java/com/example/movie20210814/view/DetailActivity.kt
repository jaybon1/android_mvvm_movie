package com.example.movie20210814.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movie20210814.R
import com.example.movie20210814.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        activityDetailBinding.movieId = intent.getIntExtra("movieId", 0)
        activityDetailBinding.lifecycleOwner = this

    }
}