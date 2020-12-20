package com.esmaeel.stepper

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.esmaeel.stepper.databinding.TimeStepperLayoutBinding
import com.esmaeel.stepper.ktxs.*


class StepperInputView @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private var binder: TimeStepperLayoutBinding? = null

    var contract: ((h: Int, d: Int) -> Unit)? = null
        set(value) {
            field = value
        }

    fun setOnValuesChangeListener(listener: (h: Int, d: Int) -> Unit) {
        contract = listener
    }

    var h_min = 0
        set(value) {
            field = value
        }

    var d_min = 0
        set(value) {
            field = value
        }

    var h_max = 24
        set(value) {
            field = value
        }

    var d_max = 30
        set(value) {
            field = value
        }

    var h: Int = h_min
        set(value) {
            field = value
            binder?.hText?.text = value.toString()
            contract?.invoke(value, d)
        }

    var d: Int = d_min
        set(value) {
            field = value
            binder?.dText?.text = value.toString()
            contract?.invoke(h, value)
        }

    var hUpDrawable: Drawable? = getDrawable(R.drawable.ic_up)
        set(value) {
            field = value
            binder?.hUp?.setImageDrawable(value)
            invalidate()
        }
    var hDownDrawable: Drawable? = getDrawable(R.drawable.ic_down)
        set(value) {
            field = value
            binder?.hDown?.setImageDrawable(value)
            invalidate()
        }

    var dUpDrawable: Drawable? = getDrawable(R.drawable.ic_up)
        set(value) {
            field = value
            binder?.dUp?.setImageDrawable(value)
            invalidate()
        }

    var dDownDrawable: Drawable? = getDrawable(R.drawable.ic_down)
        set(value) {
            field = value
            binder?.dDown?.setImageDrawable(value)
            invalidate()
        }

    var cardBgColor: Int = R.color.colorPrimary
        set(value) {
            field = value
            binder?.card0?.setBackgroundColorEX(value)
            binder?.card1?.setBackgroundColorEX(value)
            invalidate()
        }


    var hTitleText = ""
        set(value) {
            field = value
            binder?.hTitle?.text = value
            invalidate()
        }

    var dTitleText = ""
        set(value) {
            field = value
            binder?.dTitle?.text = value
            invalidate()
        }

    var hTitleVisible = false
        set(value) {
            field = value
            binder?.hTitle?.visibility(value)
            invalidate()
        }

    var dTitleVisible = false
        set(value) {
            field = value
            binder?.dTitle?.visibility(value)
            invalidate()
        }

    init {
        binder = TimeStepperLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        updateStyle()
        post {
            initClicks()
        }
    }

    private fun updateStyle() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StepperInputView)

        typedArray.getString(R.styleable.StepperInputView_hTitleText)
            .doIfProvided<String> { hTitleText = it }

        typedArray.getString(R.styleable.StepperInputView_dTitleText)
            .doIfProvided<String> { dTitleText = it }

        typedArray.getInt(R.styleable.StepperInputView_hMin, h_min)
            .doIfProvided<Int> { h_min = it }

        typedArray.getInt(R.styleable.StepperInputView_dMin, d_min)
            .doIfProvided<Int> { d_min = it }


        typedArray.getInt(R.styleable.StepperInputView_hMax, h_max)
            .doIfProvided<Int> { h_max = it }

        typedArray.getInt(R.styleable.StepperInputView_dMax, d_max)
            .doIfProvided<Int> { d_max = it }


        typedArray.getInt(R.styleable.StepperInputView_hValue, h)
            .doIfProvided<Int> { h = it }

        typedArray.getInt(R.styleable.StepperInputView_dValue, d)
            .doIfProvided<Int> { d = it }


        typedArray.getDrawable(R.styleable.StepperInputView_hUpDrawable)
            .doIfProvided<Drawable> {
                hUpDrawable = it
            }

        typedArray.getDrawable(R.styleable.StepperInputView_hDownDrawable)
            .doIfProvided<Drawable> {
                hDownDrawable = it
            }

        typedArray.getDrawable(R.styleable.StepperInputView_dUpDrawable)
            .doIfProvided<Drawable> {
                dUpDrawable = it
            }

        typedArray.getDrawable(R.styleable.StepperInputView_dDownDrawable)
            .doIfProvided<Drawable> {
                dDownDrawable = it
            }

        typedArray.getColor(R.styleable.StepperInputView_cardBgColor, 0)
            .doIfProvided<Int> {
                cardBgColor = it
            }

        typedArray.getBoolean(R.styleable.StepperInputView_hTitleVisible, false)
            .doIfProvided<Boolean> {
                hTitleVisible = it
            }

        typedArray.getBoolean(R.styleable.StepperInputView_dTitleVisible, false)
            .doIfProvided<Boolean> {
                dTitleVisible = it
            }

        typedArray.recycle()
    }


    private fun initClicks() {
        binder?.let {
            with(it) {
                hUp.setOnClickListener { hUp() }
                hDown.setOnClickListener { hDown() }
                dUp.setOnClickListener { dUp() }
                dDown.setOnClickListener { dDown() }
            }
        }
    }

    private fun hDown() {
        if (h > h_min) {
            binder?.hText.apply {
                h -= 1
                this?.slideDown<TextView>(onFinish = {
                    it.text = h.toString()
                })
            }
        }
    }

    private fun dDown() {
        if (d > d_min) {
            binder?.dText.apply {
                d -= 1
                this?.slideDown<TextView>(onFinish = {
                    it.text = d.toString()
                })
            }
        }
    }

    private fun hUp() {
        if (h < h_max) {
            binder?.hText.apply {
                h += 1
                this?.text = h.toString()
                this?.slideUp<TextView>()
            }
        }
    }

    private fun dUp() {
        if (d < d_max) {
            binder?.dText.apply {
                d += 1
                this?.text = d.toString()
                this?.slideUp<TextView>()

            }
        }
    }

}