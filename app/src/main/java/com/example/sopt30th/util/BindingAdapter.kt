package com.example.sopt30th.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setImageResId(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("imgGlideStr")
    fun setGlideImgString(imageView: ImageView, image:String) {
        Glide.with(imageView.context)
            .load(image)
            .circleCrop()
            .into(imageView)
    }
}