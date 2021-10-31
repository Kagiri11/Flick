package com.example.flick.ui.utils

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.TextView
import androidx.palette.graphics.Palette
import com.bosphere.fadingedgelayout.FadingEdgeLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DominantColorExtractor(
    var fadingEdgeLayout: FadingEdgeLayout,
    var movieName: TextView,
    var movieRating: TextView
) : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        Log.e("NowPlayingMoviesAdapter", "Error loading image")
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        val imageBitmapDrawable = resource as BitmapDrawable
        val imageBitmap = imageBitmapDrawable.bitmap

        Palette.from(imageBitmap).maximumColorCount(20).generate {
            val vibrantSwatch = it?.vibrantSwatch
            val dominantSwatch = it?.dominantSwatch!!

            if (vibrantSwatch != null) {
                fadingEdgeLayout.setBackgroundColor(vibrantSwatch.rgb)
                movieName.setTextColor(vibrantSwatch.titleTextColor)
                movieRating.setTextColor(vibrantSwatch.titleTextColor)
            } else {
                fadingEdgeLayout.setBackgroundColor(dominantSwatch.rgb)
                movieName.setTextColor(dominantSwatch.titleTextColor)
                movieRating.setTextColor(dominantSwatch.titleTextColor)
            }
        }
        return false
    }
}
