package com.esmaeel.stepper.ktxs

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

fun View.getDrawable(dr: Int): Drawable {
    return ContextCompat.getDrawable(this.context, dr) ?: ColorDrawable(0)
}


fun <T : Any> Any?.doIfProvided(gf: (value: T) -> Unit) {
    this?.let {
        if (this != 0) {
            gf(this as T)
        }
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.visibility(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

//fun MaterialCardView.setBackgroundColorEX(colorId: Int) =
//    setCardBackgroundColor(ContextCompat.getColorStateList(this.context, colorId))

fun MaterialCardView.setBackgroundColorEX(@ColorInt colorId: Int) =
    setCardBackgroundColor(colorId)

