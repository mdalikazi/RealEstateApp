package alikazi.com.sentia.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

/**
 * Created by kazi_ on 15-Apr-18.
 */
class RecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0

    private var mContext = context
    private var mAnimate: Boolean = false
    private var mListItems: ArrayList<Any>? = null

    fun setListItems(listItems: ArrayList<Any>) {
        mListItems = listItems
        mAnimate = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                // TODO RETURN VIEWHOLDER
//                return
                TODO()
            }
            else -> throw RuntimeException("There are invalid views inside RecyclerAdapter!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        animateList(holder.itemView)
        val adapterPosition = holder.adapterPosition
        when (holder.itemViewType) {
            VIEW_TYPE_ITEM -> {

            }
        }
    }

    override fun getItemCount(): Int {
        if (mListItems != null) {
           return mListItems!!.size
        }

        return 0
    }

    private fun animateList(view: View) {
        if (!mAnimate) {
            return
        }
        val translateAnimation = TranslateAnimation(0f, 0f, 600f, 0f)
        translateAnimation.interpolator = DecelerateInterpolator()
        translateAnimation.duration = 700
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

}