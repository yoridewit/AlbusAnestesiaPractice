package com.albusanesthesia.albusanesthesia

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.activity_spoedbundel_index.*
import kotlinx.android.synthetic.main.activity_spoedbundel_start.*


class Spoedbundel : AppCompatActivity() {

    private var selectedView : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spoedbundel_start)

        setupAnimations()


    }

    private fun setupAnimations() {
        selectedView = null

        constraintChapters.setOnClickListener {
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
                selectedView = imgBaby
            } else
                toDefault()
        }
    }

    private fun toDefault() {
        if (selectedView != null) {
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