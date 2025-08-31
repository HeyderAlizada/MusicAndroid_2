package com.example.mvvmsongagain

import android.widget.ImageView
import com.squareup.picasso.Picasso as PicassoLib

object PicassoHelper {

    fun addImage(url: String, target: ImageView) {
        PicassoLib.get()
            .load(url)
            .into(target)
    }
}
