package com.teamttdvlp.goodthanbefore.schoolsupport.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.drawgraphmodule.Point
import com.teamttdvlp.goodthanbefore.schoolsupport.support.UnitConverter.DpToPixel
import java.lang.Exception
import java.math.BigDecimal

class GraphDrawer : View {

    var start_X_value : Float = 0.0f

    var end_X_value : Float = 0.0f

    var step : BigDecimal = BigDecimal("0.05")

    var processer : ValueProcesser? = null

    var prev_point : Point = Point()

    var graph_paint : Paint = Paint()

    var axis_paint : Paint = Paint()

    var caro_paint : Paint = Paint()

    var thicker_caro_paint : Paint = Paint()

    var number_text_paint : TextPaint = TextPaint()

    var zoom_index : Int = 50

    var indentify_x_points = ArrayList<Double>()

    constructor(context: Context) : super(context) {
        setUp()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setUp()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setUp () {
        axis_paint.strokeWidth = DpToPixel(3).toFloat()

        graph_paint.strokeWidth = DpToPixel(3).toFloat()

        graph_paint.color = Color.BLUE

        number_text_paint.textSize = DpToPixel(20).toFloat()
        number_text_paint.color = Color.BLACK
        number_text_paint.typeface = Typeface.DEFAULT_BOLD

        caro_paint.strokeWidth = DpToPixel(0.75).toFloat()
        caro_paint.color = Color.GRAY

        thicker_caro_paint.strokeWidth = DpToPixel(2).toFloat()
        thicker_caro_paint.color = Color.GRAY
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun draw () {
        invalidate()
    }

    fun drawBackground (canvas : Canvas) {

        var index_length = DpToPixel(5).toFloat()

        axis_paint.color = Color.BLACK

        fun drawHorizontalAxisIndex () {
            number_text_paint.textAlign = Paint.Align.CENTER
            var num = 1
            for (x in width/2 + zoom_index..width step zoom_index ) {
                var fl_x = x.toFloat()
                canvas.drawLine(fl_x, height/2 - index_length, x.toFloat(), height/2 + index_length, axis_paint)
                canvas.drawLine(width - fl_x, height/2 - index_length,width - fl_x, height/2 + index_length, axis_paint)

                if (num % 5 == 0) {
                    canvas.drawLine(fl_x, 0f, fl_x, height.toFloat(), thicker_caro_paint)
                    canvas.drawLine(width - fl_x, 0f,width - fl_x, height.toFloat(), thicker_caro_paint)
                    canvas.drawText(num.toString(), fl_x, (height/2 - DpToPixel(10)).toFloat(), number_text_paint)
                    canvas.drawText((-num).toString(), width - fl_x, (height/2 - DpToPixel(10)).toFloat(), number_text_paint)
                } else {
                    canvas.drawLine(fl_x, 0f, fl_x, height.toFloat(), caro_paint)
                    canvas.drawLine(width - fl_x, 0f,width - fl_x, height.toFloat(), caro_paint)
                }

                num++
            }
        }

        fun drawVerticalAxisIndex () {
            number_text_paint.textAlign = Paint.Align.LEFT
            var num = 1
            for (y in height/2 + zoom_index..height step zoom_index ) {
                var fl_y = y.toFloat()
                canvas.drawLine(width/2 - index_length, fl_y , width/2 + index_length, fl_y, axis_paint)
                canvas.drawLine(width/2 - index_length, height - fl_y , width/2 + index_length,height - fl_y, axis_paint)

                if (num % 5 == 0) {
                    canvas.drawLine(0f, fl_y , width.toFloat(), fl_y, thicker_caro_paint)
                    canvas.drawLine(0f, height - fl_y , width.toFloat(),height - fl_y, thicker_caro_paint)
                    canvas.drawText((-num).toString(), (width/2 + DpToPixel(10)).toFloat(), fl_y + DpToPixel(5), number_text_paint)
                    canvas.drawText(num.toString(), (width/2 + DpToPixel(10)).toFloat(), height - fl_y + DpToPixel(5), number_text_paint)
                } else {
                    canvas.drawLine(0f, fl_y , width.toFloat(), fl_y, caro_paint)
                    canvas.drawLine(0f, height - fl_y , width.toFloat(),height - fl_y, caro_paint)
                }
                num ++
            }
        }

        fun drawAxis () {
            canvas.drawLine((width/2).toFloat(), 0f, (width/2).toFloat(), height.toFloat(), axis_paint)

            canvas.drawLine(0f, (height/2).toFloat(), width.toFloat(), (height/2).toFloat(), axis_paint)

            canvas.drawPoint(width/2f, height/2f, axis_paint)
        }

        drawVerticalAxisIndex()
        drawHorizontalAxisIndex()
        drawAxis()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (processer == null) return

        drawBackground(canvas!!)

        prev_point.x = start_X_value * zoom_index + width/2

        prev_point.y = height/2 - processer!!.process(start_X_value) * zoom_index

        // It's inside loop, it just run once each time onDraw is called
        var x_value = BigDecimal(start_X_value.toString())

        x_value += step

        var errorHappened = false

        var bigdecimal_end_x_value = BigDecimal(end_X_value.toString())

        // x_value <= end_x value
        while (x_value.compareTo(bigdecimal_end_x_value) <= 0) {

            try {

                Log.e("x val", x_value.toString())
                var y = height/2 - processer!!.process(x_value.toFloat()) * zoom_index
                if (y.isNaN()) throw NotANumberException()
                Log.e("It still run: ", processer!!.process(x_value.toFloat()).toString())

                var x = x_value.toFloat() * zoom_index + width/2

                if (indentify_x_points.size > 0) {
                    for (error_x_point in indentify_x_points)
                        if (x_value.toDouble() == error_x_point) throw Exception("Math Error Exception")
                }

                if (!errorHappened) {
                    canvas!!.drawLine(prev_point.x, prev_point.y, x, y, graph_paint)
                }

                else {

                    var prev_point_y = height/2 - processer!!.process(prev_point.x) * zoom_index
                    prev_point.x = prev_point.x * zoom_index + width/2
                    canvas!!.drawLine(prev_point.x, prev_point_y, x, y, graph_paint)
                    errorHappened = false

                }

                prev_point.x = x

                prev_point.y = y

                x_value += step

            } catch (e : Exception) {
                e.printStackTrace()
                Log.e("ERROR HAPPEN", x_value.toString() + " is Error")
                errorHappened = true
                // Next x value is stored in prev to start new line after an error happen
                x_value += step
                if (x_value.compareTo(bigdecimal_end_x_value) <= 0)

                prev_point.x = x_value.toFloat()

                x_value += step
            }

        }


    }

    interface ValueProcesser {
        fun process (x : Float) : Float
    }

    class NullValueProcesserException : Exception () {
        override val message: String = "You must implement a processer to process Y value from X value"
    }

    class MathErrorOf_HamSoHuuTiBac1 : Exception() {
        override val message: String?
            get() = "Divide 0"
    }

    class NotANumberException : Exception() {
        override val message: String?
            get() = "Not a number"
    }
}
