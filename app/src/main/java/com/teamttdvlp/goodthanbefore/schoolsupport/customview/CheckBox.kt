package com.teamttdvlp.goodthanbefore.schoolsupport.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.CheckBox
import com.teamttdvlp.goodthanbefore.schoolsupport.R


class CheckBox : CheckBox {

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
            val properties = context.obtainStyledAttributes(attrs, R.styleable.DrawableCheckBox)
            checked_background = properties.getDrawable(R.styleable.DrawableCheckBox_checked_background)
            unchecked_background = properties.getDrawable(R.styleable.DrawableCheckBox_unchecked_background)
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
        val newListener = OnCheckedChangeListener { buttonView, isChecked ->
            changeState()
            listener?.onCheckedChanged(buttonView, isChecked)
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

