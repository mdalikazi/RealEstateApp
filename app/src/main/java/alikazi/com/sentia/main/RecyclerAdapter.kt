package alikazi.com.sentia.main

import alikazi.com.sentia.R
import alikazi.com.sentia.models.Properties
import alikazi.com.sentia.utils.AppConf
import alikazi.com.sentia.utils.DLog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView

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
                val propertyViewHolder: PropertyViewHolder = holder as PropertyViewHolder
                propertyViewHolder.title.text = mListItems?.data?.get(adapterPosition)?.title
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

        var title: TextView = itemView.findViewById(R.id.property_item_title)

    }

}