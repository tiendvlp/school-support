package com.teamttdvlp.goodthanbefore.schoolsupport.support

import android.content.res.Resources

object UnitConverter {

    internal var density: Float

    val _5dp: Int

    val _10dp: Int

    val _20dp: Int

    val _30dp: Int

    val _50dp: Int

    val _100dp: Int


    init {
        density = Resources.getSystem().displayMetrics.density
        _5dp = DpToPixel(5)
        _10dp = DpToPixel(10)
        _20dp = DpToPixel(20)
        _30dp = DpToPixel(30)
        _50dp = DpToPixel(50)
        _100dp = DpToPixel(100)
    }

    fun DpToPixel(dp: Int): Int {
        return Math.round(dp * density)
    }

    fun DpToPixel(dp: Double): Int {
        return Math.round(dp * density).toInt()
    }

    fun DpToPixel(dp: Float): Int {
        return Math.round(dp * density)
    }

    fun PixelToDp(pixel: Int): Int {
        val dp = Math.round(pixel / density)
        return pixel
    }

}
