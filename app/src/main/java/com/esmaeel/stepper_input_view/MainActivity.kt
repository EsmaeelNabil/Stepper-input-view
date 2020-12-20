package com.esmaeel.stepper_input_view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esmaeel.stepper.StepperInputView
import com.esmaeel.stepper.ktxs.hide
import com.esmaeel.stepper.ktxs.show
import com.esmaeel.stepper.ktxs.slideDown

class MainActivity : AppCompatActivity() {
    lateinit var stepper: StepperInputView
    lateinit var txt: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepper = findViewById(R.id.stepper)
        txt = findViewById(R.id.txt)

        stepper.setOnValuesChangeListener { h, d ->
            txt.slideDown<TextView>(onBefore = {
                it.hide()
            }, onFinish = {
                it.show()
                it.text = "hours: $h  ---  days: $d"
            })
        }


    }
}