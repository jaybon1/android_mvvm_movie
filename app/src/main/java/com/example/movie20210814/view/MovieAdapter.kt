package com.example.movie20210814.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movie20210814.databinding.MainListViewBinding
import com.example.movie20210814.model.MovieDTO

abstract class MovieBaseViewHolder<T>(viewBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bind(item: T)
}

class MovieAdapter(
    private val mainActivity: MainActivity,
    var movieList: List<MovieDTO>
) : RecyclerView.Adapter<MovieBaseViewHolder<*>>() {

    fun changeMovieList(movieList: List<MovieDTO>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBaseViewHolder<*> {

        val inflater = LayoutInflater.from(parent.context)
        val binding = MainListViewBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieBaseViewHolder<*>, position: Int) {
        (holder as ViewHolder).bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(
        val binding: MainListViewBinding
    ) : MovieBaseViewHolder<MovieDTO>(binding) {

        override fun bind(item: MovieDTO) {
            with(binding) {
                movieDTO = item
                layoutItem.setOnClickListener {
                    mainActivity.onItemClicked(item)
                }
                executePendingBindings()
            }
        }
    }

    companion object {
        private const val TAG = "MovieAdapter"
    }
}