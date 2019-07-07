package phamf.com.chemicalapp.CustomView.CustomViewModel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint

class Dot(private var x_pos: Int, private var y_pos: Int, var radius: Int, var title: String?, dot_color: Int, title_color: Int) {

    private val paint: Paint

    private val textPaint: TextPaint

    init {

        paint = Paint()
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = dot_color

        textPaint = TextPaint()
        textPaint.isAntiAlias = true
        textPaint.color = title_color
        textPaint.textSize = radius.toFloat()
        textPaint.textAlign = Paint.Align.RIGHT
    }

    fun drawPoint(canvas: Canvas) {
        canvas.drawCircle(x_pos.toFloat(), y_pos.toFloat(), radius.toFloat(), paint)
    }

    fun drawTitle(canvas: Canvas) {
        textPaint.textSize = (radius * 3).toFloat()
        canvas.drawText(title!!, (x_pos - radius * 5 / 2).toFloat(), y_pos + 1.5f * radius.toFloat() * 0.75f, textPaint)
    }

    fun setTextAlpha(alpha: Int) {
        textPaint.alpha = alpha
    }

    fun setTextStyle(typeface: Typeface) {
        textPaint.typeface = typeface
    }

    fun setTextColor(color: Int) {
        textPaint.color = color
    }

    fun setX_pos(x_pos: Int) {
        this.x_pos = x_pos
    }

    fun setY_pos(y_pos: Int) {
        this.y_pos = y_pos
    }

    fun setPaintColor(color: Int) {
        this.paint.color = color
    }

}
