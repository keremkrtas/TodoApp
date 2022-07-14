package com.keremkaratas.todoapp.view.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String) {
        Picasso.get().load(url).into(this)
    }
