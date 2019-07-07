package com.teamttdvlp.goodthanbefore.schoolsupport.customview

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

class ExtremeAndSolutionDrawer : View {

    var extreme_list : ArrayList<Point> = ArrayList()

    var solutions_list : ArrayList<Point> = ArrayList()

    var zoom_index : Int = 70

    var point_paint = Paint()

    var name_point_paint = TextPaint()

    var asymptotic_paint = Paint()

    var isDrawExtreme : Boolean = true

    var isDrawSolutions : Boolean = true

    var isDrawAsymptoticLine: Boolean = false

    var start_X_value : Float = 0.0f

    var end_X_value : Float = 0.0f

    // Tiệm cận đứng
    var vertical_asymtotic = 0.0f

    // Tiệm cận ngang
    var horizontal_asymtotic = 0.0f

    constructor(context : Context) : super(context)

    constructor(context : Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        point_paint.color = Color.parseColor("#f3003e")
        point_paint.strokeWidth = DpToPixel(7).toFloat()
        point_paint.strokeCap = Paint.Cap.ROUND

        asymptotic_paint.color = Color.RED
        asymptotic_paint.strokeWidth = DpToPixel(3).toFloat()

        name_point_paint.color = Color.parseColor("#f3003e")
        name_point_paint.textSize = DpToPixel(20).toFloat()
        name_point_paint.typeface = Typeface.DEFAULT_BOLD
        name_point_paint.textAlign = Paint.Align.CENTER
    }

    fun switchSolutions () {
        isDrawSolutions = !isDrawSolutions
        invalidate()
    }

    fun switchExtreme () {
        isDrawExtreme = !isDrawExtreme
        invalidate()
    }


    fun draw () {
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isDrawAsymptoticLine) {
            canvas!!.drawLine(0f, height/2 - horizontal_asymtotic*zoom_index , width.toFloat(), height/2 - horizontal_asymtotic * zoom_index, asymptotic_paint)
            canvas.drawLine(vertical_asymtotic * zoom_index + width/2, 0f, vertical_asymtotic * zoom_index  + width/2, height.toFloat(), asymptotic_paint)
        }

        if (isDrawExtreme)
            for ((pos,point)  in extreme_list.withIndex()) {
                if (point.x in start_X_value..end_X_value) {
                    canvas!!.drawPoint(point.x * zoom_index + width/2, height/2 - point.y * zoom_index , point_paint)
                    canvas.drawText((pos + 65).toByte().toChar().toString(), point.x * zoom_index + width/2, height/2 - point.y * zoom_index - DpToPixel(15), name_point_paint)
                }
            }

        if (isDrawSolutions)
            for ((pos, point) in solutions_list.withIndex()) {
                if (point.x in start_X_value..end_X_value) {
                    canvas!!.drawPoint(point.x * zoom_index + width/2, height/2 - point.y * zoom_index , point_paint)
                    canvas.drawText("x$pos", point.x * zoom_index + width/2, height/2 - point.y * zoom_index - DpToPixel(15), name_point_paint)
                }
            }


    }

    fun switchAsymptoticLine() {
        isDrawAsymptoticLine = !isDrawAsymptoticLine
        invalidate()
    }
}