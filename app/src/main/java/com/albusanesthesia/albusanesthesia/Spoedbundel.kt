package com.albusanesthesia.albusanesthesia

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spoedbundel_start.*


class Spoedbundel : AppCompatActivity() {

    private var selectedView : View? = null
    var enteredWeight = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spoedbundel_start)

        editTextWeight.isFocusable = true

        editTextWeight.setOnEditorActionListener { v, actionId, event ->
            try {
                enteredWeight = editTextWeight.text.toString().toInt()
                textViewWeight.text = enteredWeight.toString()
                hideKeyboard()

                updateConstraints(R.layout.activity_spoedbundel_choice_finished)
                selectedView = textViewWeight


            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show()
            }
            true
        }

        setupAnimations()

    }


    //Functions

    private fun setupAnimations() {
        selectedView = null

        root.setOnClickListener {
            toDefault()
        }

        imgBaby.setOnClickListener {
            if (selectedView != imgBaby) {
                updateConstraints(R.layout.activity_spoedbundel_start_baby)
                selectedView = imgBaby
            } else
                toDefault()
        }

        imgAdult.setOnClickListener {
            if (selectedView != imgAdult) {
                updateConstraints(R.layout.activity_spoedbundel_start_adult)
                selectedView = imgAdult
            } else
                toDefault()
        }

        btnEnterWeight.setOnClickListener {
            when (selectedView) {
                imgAdult -> {
                    updateConstraints(R.layout.activity_spoedbundel_start_weight_adult)
                    selectedView = btnEnterWeight
                    showSoftKeyboard(this, editTextWeight)
                }
                imgBaby -> {
                    updateConstraints(R.layout.activity_spoedbundel_start_weight_baby)
                    selectedView = btnEnterWeight
                    showSoftKeyboard(this, editTextWeight)
                }
                else -> Toast.makeText(this, "Please select spoedbundel type", Toast.LENGTH_LONG).show()
            }
        }

        btnNoWeight.setOnClickListener {
            when (selectedView) {
                imgAdult -> {
                    updateConstraints(R.layout.activity_spoedbundel_start_weight_adult)
                    selectedView = btnEnterWeight
                    showSoftKeyboard(this, editTextWeight)
                }
                imgBaby -> {
                    updateConstraints(R.layout.activity_spoedbundel_start_weight_baby)
                    selectedView = btnEnterWeight
                    showSoftKeyboard(this, editTextWeight)
                }
                else -> Toast.makeText(this, "Please select spoedbundel type", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun toDefault() {
        if (selectedView != null && enteredWeight < 0) {
            hideKeyboard()
            updateConstraints(R.layout.activity_spoedbundel_start)
            selectedView = null
        } else if (selectedView != null && enteredWeight > 0) {
            hideKeyboard()
            updateConstraints(R.layout.activity_spoedbundel_start)
            selectedView = null
        }
    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(root)

        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(root, transition)
    }

    fun showSoftKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN, 0)
    }

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            Log.e("Error:", "Error hiding the keyboard")
        }

    }
}








//val weight = etWeight.text
//
//        //enables manipulation on user entered values of edittext weight
//        etWeight.setOnEditorActionListener { etWeight, actionId, event ->
//
//            //whenkeyboard press "done"
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                //check if a number is entered
//                if (TextUtils.isDigitsOnly(weight)) {
//
//                    TransitionManager.beginDelayedTransition(transitionContainer)
//                    etWeight.visibility = View.INVISIBLE
//
//                } else {
//                    Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//
//            etWeight.setTextColor(ContextCompat.getColor(this@Spoedbundel, R.color.validColorGreen))
//            true
//        }