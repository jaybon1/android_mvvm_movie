package com.example.movie20210814.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageByGlide(
    imageView: ImageView,
    url: String
){
    Glide.with(imageView.context).load(url).into(imageView)
}