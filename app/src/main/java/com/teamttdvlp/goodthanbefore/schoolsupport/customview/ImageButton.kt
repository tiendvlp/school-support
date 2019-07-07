package com.teamttdvlp.goodthanbefore.schoolsupport.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton


/**
 * This class is created for reducing drawable's count
 * We don't need to create clicked_state_drawable of button when it is clicked as well as
 * the .xml file selector
 */
class ImageButton : ImageButton {

    private val fade_out_anim : Animation = AlphaAnimation(0.6f, 1.0f).apply {
        duration = 200
        fillAfter = true
    }

    var mOnClickListener : View.OnClickListener? = null

    constructor(context : Context) : super(context) {
        setOnTouchListener(null)
    }

    constructor(context : Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setOnTouchListener(null)
    }

    override fun setOnTouchListener(l: OnTouchListener?) {

        var newOnTouchListener = OnTouchListener {
            view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                view.alpha = 0.6f
                return@OnTouchListener true
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                view.alpha = 1.0f
                startAnimation(fade_out_anim)
                mOnClickListener?.onClick(this)
                return@OnTouchListener true
            }

            l?.let {
                return@OnTouchListener it.onTouch(view, motionEvent)
            }

            false
        }

        super.setOnTouchListener(newOnTouchListener)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this.mOnClickListener = l
    }

}