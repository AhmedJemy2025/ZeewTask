package com.app.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter(value = ["imageUrl"])
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
            .load(imageUrl)
            .centerInside()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(view)
}

