package alikazi.com.sentia.main

import alikazi.com.sentia.R
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImagePagerAdapter(private var mContext: Context) : PagerAdapter() {

    private var mPropertyImageUrls: ArrayList<String>? = ArrayList()

    fun setPagerImageUrls(imageUrls: ArrayList<String>?) {
        mPropertyImageUrls = imageUrls
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mPropertyImageUrls?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.property_image_pager_item, container, false)
        val dealImageView: ImageView = itemView.findViewById(R.id.pager_item_image_view)
        Glide.with(mContext)
                .load(mPropertyImageUrls!![position])
                .apply(RequestOptions().centerInside())
                .transition(DrawableTransitionOptions().crossFade())
                .into(dealImageView)

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
