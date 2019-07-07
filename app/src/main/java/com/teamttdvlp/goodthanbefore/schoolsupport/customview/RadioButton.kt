package com.teamttdvlp.goodthanbefore.schoolsupport.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.RadioButton
import com.teamttdvlp.goodthanbefore.schoolsupport.R

class RadioButton : RadioButton {

    var checked_background : Drawable? = null

    var unchecked_background : Drawable? = null

    private var disableOnClickListener = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init (attrs: AttributeSet? = null) {
        buttonDrawable = null
        attrs?.let {
            var properties = context.obtainStyledAttributes(attrs, R.styleable.DrawableRadioButton)
            checked_background = properties.getDrawable(R.styleable.DrawableRadioButton_checked_bg)
            unchecked_background = properties.getDrawable(R.styleable.DrawableRadioButton_unchecked_bg)
            background = if (isChecked)
                checked_background
            else
                unchecked_background
            properties.recycle()
        }
        setOnCheckedChangeListener(null)
    }

    private fun changeState () {

        background = if (isChecked)
            checked_background
        else
            unchecked_background

    }

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {

        var newListener = object : OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                changeState()
                listener?.onCheckedChanged(buttonView, isChecked)
            }

        }

        super.setOnCheckedChangeListener(newListener)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (!disableOnClickListener) {
            super.setOnClickListener(l)
            disableOnClickListener = true
        }
    }
}
