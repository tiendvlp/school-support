package com.teamttdvlp.goodthanbefore.schoolsupport.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText

class VirtualKeyBoardSensor : AppCompatEditText {

    private var onHideListener: OnHideVirtualKeyboardListener? = null

    private var liteOnTextChangeListener: OnTextChangeLite? = null

    constructor(context: Context) : super(context) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (liteOnTextChangeListener != null) {
                    liteOnTextChangeListener!!.onTextChange(s, start, before, count)
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (liteOnTextChangeListener != null) {
                    liteOnTextChangeListener!!.onTextChange(s, start, before, count)
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    fun setOnHideVirtualKeyboardListener(listener: OnHideVirtualKeyboardListener) {
        this.onHideListener = listener
    }

    fun addLiteTextChangeListener(onTextChangeListener: OnTextChangeLite) {
        this.liteOnTextChangeListener = onTextChangeListener
    }


    /** Catch event user click turn off virtual keyboard  */
    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (onHideListener != null) {
                onHideListener!!.onHide()
            }
        }
        return super.dispatchKeyEvent(event)
    }


    /** INTERFACE  */
    interface OnHideVirtualKeyboardListener {
        fun onHide()
    }


    /** This interface is used to hide 2 unused override function of TextWatcherListener interface
     * This make code seem shorter and easier to read
     */
    interface OnTextChangeLite {
        fun onTextChange(s: CharSequence, start: Int, before: Int, count: Int)
    }
}
