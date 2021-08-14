package com.example.movie20210814.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie20210814.R
import com.example.movie20210814.databinding.ActivityMainBinding
import com.example.movie20210814.model.MovieDTO
import com.example.movie20210814.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        initViewModel()

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.movieViewModel = movieViewModel
        activityMainBinding.lifecycleOwner = this

        movieAdapter = MovieAdapter(this, listOf())

        activityMainBinding.rvMain.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    override fun onResume() {
        super.onResume()
        movieViewModel.getUserData()
        movieViewModel.getList()

    }


    private fun initViewModel() {
        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MovieViewModel::class.java)

        movieViewModel.bindMovieDTOListLive().observe(this, {
            movieAdapter.changeMovieList(it)
        })

    }

    fun onItemClicked(item: MovieDTO) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieId", item.id)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "Main_Activity"
    }
}