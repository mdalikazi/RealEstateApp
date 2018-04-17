package alikazi.com.sentia.main

import alikazi.com.sentia.R
import alikazi.com.sentia.models.Properties
import alikazi.com.sentia.models.Property
import alikazi.com.sentia.utils.AppConf
import alikazi.com.sentia.utils.DLog
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Created by kazi_ on 15-Apr-18.
 */
class RecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val LOG_TAG = AppConf.LOG_TAG_MAIN
        private const val VIEW_TYPE_ITEM = 0
    }

    private var mContext = context
    private var mAnimate: Boolean = false
    private var mListItems: Properties? = null

    fun setListItems(listItems: Properties) {
        DLog.i(LOG_TAG, "setListItems")
        mListItems?.data?.clear()
        notifyDataSetChanged()
        mListItems = listItems
        mAnimate = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.property_item, parent, false)
                return PropertyViewHolder(view)
            }
            else -> throw RuntimeException("There are invalid views inside RecyclerAdapter!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        animateList(holder.itemView)
        val adapterPosition = holder.adapterPosition
        when (holder.itemViewType) {
            VIEW_TYPE_ITEM -> {
                val viewHolder: PropertyViewHolder = holder as PropertyViewHolder
                val property: Property? = mListItems?.data?.get(adapterPosition)

                Glide.with(mContext)
                        .load(property?.photo?.image?.url)
                        .transition(DrawableTransitionOptions().crossFade())
                        .apply(RequestOptions().encodeQuality(100))
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                viewHolder.photoProgressBar.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                viewHolder.photoProgressBar.visibility = View.GONE
                                return false
                            }
                        })
                        .into(viewHolder.propertyPhoto)
                viewHolder.title.text = property?.title
                viewHolder.address.text =
                        property?.location?.address_1 + "\n" +
                        property?.location?.suburb + " " +
                        property?.location?.state + " " +
                        property?.location?.postcode
                viewHolder.ownerName.text = property?.owner?.first_name + " " + property?.owner?.last_name
                Glide.with(mContext)
                        .load(property?.owner?.avatar?.url)
                        .transition(DrawableTransitionOptions().crossFade())
                        .apply(RequestOptions().circleCrop().placeholder(R.drawable.ic_account))
                        .into(viewHolder.ownerAvatar)
                viewHolder.propertyBedrooms.text = property?.bedrooms.toString()
                viewHolder.propertyBathrooms.text = property?.bathrooms.toString()
                viewHolder.propertyCarspots.text = property?.carspots.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        if (mListItems != null) {
           return mListItems!!.data.size
        }

        return 0
    }

    private fun animateList(view: View) {
        if (!mAnimate) {
            return
        }
        val translateAnimation = TranslateAnimation(0f, 0f, 500f, 0f)
        translateAnimation.interpolator = DecelerateInterpolator()
        translateAnimation.duration = 500
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                // Animate only once at the start
                mAnimate = false
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(translateAnimation)
    }

    private class PropertyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var propertyPhoto: ImageView = itemView.findViewById(R.id.property_item_photo)
        var photoProgressBar: ProgressBar = itemView.findViewById(R.id.property_item_progress_bar)
        var title: TextView = itemView.findViewById(R.id.property_item_title)
        var address: TextView = itemView.findViewById(R.id.property_item_address)
        var ownerAvatar: ImageView = itemView.findViewById(R.id.property_item_owner_avatar)
        var ownerName: TextView = itemView.findViewById(R.id.property_item_owner_name)
        var propertyBedrooms: TextView = itemView.findViewById(R.id.property_item_number_of_bedrooms)
        var propertyBathrooms: TextView = itemView.findViewById(R.id.property_item_number_of_bathrooms)
        var propertyCarspots: TextView = itemView.findViewById(R.id.property_item_number_of_carspots)
        var follow: CheckBox = itemView.findViewById(R.id.property_item_heart_checkbox)

    }

}
