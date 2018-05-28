package alikazi.com.codesample.propertyapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class CustomViewUtils {

    companion object {
        fun showCircularPhotoWithGlide(context: Context, url: String?, placeholder: Int, target: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(DrawableTransitionOptions().crossFade())
                    .apply(RequestOptions().circleCrop().placeholder(placeholder))
                    .into(target)
        }
    }
}
