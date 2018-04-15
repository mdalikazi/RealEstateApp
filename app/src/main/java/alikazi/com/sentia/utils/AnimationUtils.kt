package alikazi.com.sentia.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.animation.DecelerateInterpolator

/**
 * Created by kazi_ on 15-Apr-18.
 */
class AnimationUtils {

    companion object {
        fun animateToolbar(context: Context, toolbar: Toolbar, listener: ToolbarAnimationListener) {
            DLog.i(AppConf.LOG_TAG_MAIN, "animateToolbar")
            val layoutParams = toolbar.layoutParams
            val toolbarHeight = layoutParams.height
            val valueAnimator = ValueAnimator.ofInt(toolbarHeight, getDefaultActionBarHeightInPixels(context))
            valueAnimator.duration = 400
            valueAnimator.startDelay = 500
            valueAnimator.interpolator = DecelerateInterpolator()
            valueAnimator.setTarget(toolbar)
            valueAnimator.addUpdateListener { valueAnimator ->
                val lp = toolbar.layoutParams
                val animatedHeight = valueAnimator.animatedValue
                if (animatedHeight is Float) {
                    lp.height = animatedHeight.toInt()
                }
                toolbar.layoutParams = lp
            }

            valueAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    listener.onToolbarAnimationEnd()
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })
            valueAnimator.start()
        }

        fun getDefaultActionBarHeightInPixels(context: Context): Int {
            DLog.i(AppConf.LOG_TAG_MAIN, "getDefaultActionBarHeightInPixels")
            val typedValue = TypedValue()
            val canGetValue = context.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
            return if (canGetValue) {
                TypedValue.complexToDimensionPixelSize(typedValue.data, context.resources.displayMetrics)
            } else 0
        }
    }

    interface ToolbarAnimationListener {
        fun onToolbarAnimationEnd()
    }
}