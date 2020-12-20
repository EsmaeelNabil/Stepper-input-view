@file:Suppress("UNCHECKED_CAST")

package com.esmaeel.stepper.ktxs

import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation


fun <T : View> View.slideUp(
    duration: Long = 250,
    onBefore: ((View: T) -> Unit)? = null,
    onStart: ((View: T) -> Unit)? = null,
    onRepeat: ((View: T) -> Unit)? = null,
    onFinish: ((View: T) -> Unit)? = null,
) {
    val view = this as T
    onBefore?.invoke(view)
    this.startAnimation(
        TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            this.height.toFloat(),  // fromYDelta
            0f).apply {
            this.duration = duration
            this.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    onStart?.invoke(view)
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onFinish?.invoke(view)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    onRepeat?.invoke(view)
                }

            })
        }
    )

}

fun <T : View> View.slideDown(
    duration: Long = 250,
    onBefore: ((View: T) -> Unit)? = null,
    onStart: ((View: T) -> Unit)? = null,
    onRepeat: ((View: T) -> Unit)? = null,
    onFinish: ((View: T) -> Unit)? = null,
) {
    val view = this as T
    onBefore?.invoke(view)
    this.startAnimation(
        TranslateAnimation(
            0f,
            0f,
            0f,
            this.height.toFloat()
        ).apply {
            this.duration = duration
            this.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    onStart?.invoke(view)
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onFinish?.invoke(view)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    onRepeat?.invoke(view)
                    view.clearAnimation()
                }

            })
        }
    )

}

fun View.rotate(
    rotation: Float = 90f,
    speed: Long = 150,
    interpolator: TimeInterpolator = AccelerateInterpolator(),
    withEndAction: (() -> Unit)? = null,
) {
    this.animate()
        .rotation(rotation)
        .withEndAction(withEndAction)
        .setDuration(speed).interpolator = interpolator
}

fun View.fadeOut(
    speed: Long = 250,
    interpolator: TimeInterpolator = AccelerateInterpolator(),
    withEndAction: (() -> Unit)? = null,
) {
    this.animate()
        .alpha(0f)
        .withEndAction(withEndAction)
        .setDuration(speed).interpolator = interpolator
}

fun View.fadeIn(
    speed: Long = 350,
    interpolator: TimeInterpolator = AccelerateInterpolator(),
) {
    this.animate()
        .alpha(1f)
        .setDuration(speed).interpolator = interpolator
}
