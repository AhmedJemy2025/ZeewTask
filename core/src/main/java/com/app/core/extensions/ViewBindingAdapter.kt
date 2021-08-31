package com.app.core.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@set:BindingAdapter("visible")
var View.visible
        get() = visibility == View.VISIBLE
        set(value) {
                visibility = if (value) View.VISIBLE else View.GONE
        }


