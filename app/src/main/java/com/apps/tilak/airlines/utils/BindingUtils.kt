package com.apps.tilak.airlines.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.apps.tilak.airlines.constants.AppConstants
import com.bumptech.glide.Glide

object BindingUtils {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeHolder")
    fun loadImage(view: ImageView, url: String, placeHolder: Drawable) {
        Glide.with(view.context)
            .load(AppConstants.BASE_URL + url)
            .centerCrop()
            .placeholder(placeHolder)
            .error(placeHolder)
            .into(view)
    }
}