package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.drawgraphmodule.AsymptoticFinder
import com.example.drawgraphmodule.EquationExtremeFinder
import com.example.drawgraphmodule.EquationSolver
import com.example.drawgraphmodule.Point
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.customview.GraphDrawer
import com.teamttdvlp.goodthanbefore.schoolsupport.support.processX
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity.Companion.HUU_TI_BAC_1
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity.Companion.NGU_THUC_BAC_4
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity.Companion.TAM_THUC_BAC_2
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SpecialGraphMenuActivity.Companion.TU_THUC_BAC_3
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LV_Extr_N_Sol_Adapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.GraphDrawerActivityViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.GraphInfo
import kotlinx.android.synthetic.main.activity_graph_drawer.*
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function
import java.math.BigDecimal

class GraphDrawerActivity : AppCompatActivity() {

    lateinit var viewModel : GraphDrawerActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_drawer)
        FrameLayout(this).background
        setUpGraphDrawer()

        setUpViewModel()

        addEvent()

        viewModel.loadData(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpGraphDrawer() {

        graph.start_X_value = -10f
        graph.end_X_value = 10f
        graph.step = BigDecimal("0.05")
        graph.zoom_index = 100

        graph_extreme_n_solution.zoom_index = graph.zoom_index
        graph_extreme_n_solution.start_X_value = graph.start_X_value
        graph_extreme_n_solution.end_X_value = graph.end_X_value

    }

    fun setUpViewModel () {

        viewModel = GraphDrawerActivityViewModel(application)

        viewModel.ldt_graph_type.observe(this, object : Observer<GraphInfo> {
            override fun onChanged(gr: GraphInfo?) {

                when(gr!!.graph_type) {

                    TAM_THUC_BAC_2 -> draw_ttb_2(gr)

                    TU_THUC_BAC_3 -> draw_ttb_3(gr)

                    NGU_THUC_BAC_4 -> draw_ntb_4(gr)

                    HUU_TI_BAC_1 -> draw_htb_1(gr)

                    else -> draw_some_graph(gr)
                }

                setUpFunctionsAfterDrawingGraph()
            }
        })

    }

    fun setUpFunctionsAfterDrawingGraph () {

        var sol_lv_adapter = LV_Extr_N_Sol_Adapter(this, graph_extreme_n_solution.solutions_list)
        sol_lv_adapter.describeContent { point, pos ->
            "x$pos = ".plus(point.x)
        }
        graph_lv_solutions.adapter = sol_lv_adapter

        var extr_lv_adapter = LV_Extr_N_Sol_Adapter(this, graph_extreme_n_solution.extreme_list)
        extr_lv_adapter.describeContent { point, pos ->
            var character = ((65 + pos).toByte()).toChar().toString()
            "$character (" + point.x + ", " + point.y + ")"
        }

        graph_lv_extreme.adapter = extr_lv_adapter

        graph_txt_solution_count.text = "Số nghiệm: " + graph_extreme_n_solution.solutions_list.size.toString()
        graph_txt_extreme_count.text = "Số điểm cực trị: " + graph_extreme_n_solution.extreme_list.size.toString()
    }

    fun addEvent () {

        graph_btn_back.setOnClickListener {
            finish()
        }

        graph_btn_extreme.setOnClickListener {
            graph_extreme_n_solution.switchExtreme()
            graph_btn_extreme.alpha = if (graph_btn_extreme.alpha == 1.0f) 0.75f else 1.0f
        }

        graph_btn_solution.setOnClickListener {
            graph_extreme_n_solution.switchSolutions()
            graph_btn_solution.alpha = if (graph_btn_solution.alpha == 1.0f) 0.75f else 1.0f
        }

        graph_btn_setting.setOnClickListener {
            graph_setting_form.visibility = if (graph_setting_form.visibility == View.VISIBLE) {

                var start_x_val = graph_txt_start_x_value.text.toString().toFloat()
                var end_x_val= graph_txt_end_x_value.text.toString().toFloat()
                var zoom_index = graph_txt_zoom_index.text.toString().toInt()

                if ((start_x_val != graph.start_X_value) or (end_x_val != graph.end_X_value) or (zoom_index != graph.zoom_index)) {
                    graph.start_X_value = start_x_val
                    graph.end_X_value = end_x_val
                    graph.zoom_index = 10 + zoom_index * 20
                    graph.draw()
                    graph_extreme_n_solution.start_X_value = start_x_val
                    graph_extreme_n_solution.end_X_value = end_x_val
                    graph_extreme_n_solution.zoom_index = graph.zoom_index
                    graph_extreme_n_solution.draw()
                }

                // return
                View.GONE
            } else View.VISIBLE

        }

        graph_btn_graph_info.setOnClickListener {
            graph_info_form.visibility = if (graph_info_form.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        graph_btn_plus_zoom.setOnClickListener {
            var zoom_index = graph_txt_zoom_index.text.toString().toInt()
            if (zoom_index < 10) zoom_index++
            graph_txt_zoom_index.text = zoom_index.toString()
        }

        graph_btn_minus_zoom.setOnClickListener {
            var zoom_index = graph_txt_zoom_index.text.toString().toInt()
            if (zoom_index > 1) zoom_index--
            graph_txt_zoom_index.text = zoom_index.toString()
        }

        graph_btn_plus_start_x.setOnClickListener {
            var start_x_val= graph_txt_start_x_value.text.toString().toInt()
            var end_x_val = graph_txt_end_x_value.text.toString().toInt()
            if (start_x_val + 5 < end_x_val) {
                start_x_val += 5
                graph_txt_start_x_value.text = start_x_val.toString()
            } else {
                Toast.makeText(this, "Khoảng giá trị của x phải lớn hơn 5", Toast.LENGTH_LONG).show()
            }
        }

        graph_btn_minus_start_x.setOnClickListener {
            var start_x_val = graph_txt_start_x_value.text.toString().toInt()
            graph_txt_start_x_value.text = (start_x_val - 5).toString()
        }

        graph_btn_plus_end_x.setOnClickListener {
            var end_x_val= graph_txt_end_x_value.text.toString().toInt()
            graph_txt_end_x_value.text = (end_x_val + 5).toString()
        }

        graph_btn_minus_end_x.setOnClickListener {
            var start_x_val = graph_txt_start_x_value.text.toString().toInt()
            var end_x_val = graph_txt_end_x_value.text.toString().toInt()
            if ((end_x_val - 5) > start_x_val) {
                end_x_val -= 5
                graph_txt_end_x_value.text = end_x_val.toString()
            } else {
                Toast.makeText(this, "Khoảng giá trị của x phải lớn hơn 5", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun draw_ttb_2 (graph_info : GraphInfo) {

        var abc = graph_info.graph_info!!

        graph.start_X_value = graph_info.startX
        graph.end_X_value= graph_info.endX

        var a = abc[0]
        var b = abc[1]
        var c = abc[2]

        graph.processer = object : GraphDrawer.ValueProcesser {
            override fun process(x: Float): Float = a*x*x + b*x + c
        }

        var solutions = EquationSolver.tinh_phuong_trinh_bac_2(a.toDouble(), b.toDouble(), c.toDouble())
        var extreme = EquationExtremeFinder.tim_cuc_tri_ham_bac_2(a, b)

        for (x in solutions) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.solutions_list.add(Point(x.toFloat(), y))
        }

        for (x in extreme) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.extreme_list.add(Point(x.toFloat(), y))
        }

        graph_extreme_n_solution.draw()

        graph.draw()
    }

    fun draw_ttb_3 (graph_info : GraphInfo) {

        var abcd = graph_info.graph_info!!

        graph.start_X_value = graph_info.startX
        graph.end_X_value= graph_info.endX

        var a = abcd [0]
        var b = abcd [1]
        var c = abcd [2]
        var d = abcd [3]

        graph.processer = object : GraphDrawer.ValueProcesser {
            override fun process(x: Float): Float = a*x*x*x + b*x*x + c*x + d
        }

        var solutions = EquationSolver.tinh_phuong_trinh_bac_3(a.toDouble(), b.toDouble(), c.toDouble(), d.toDouble())
        var extreme = EquationExtremeFinder.tim_cuc_tri_ham_bac_3(a, b, c)

        for (x in solutions) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.solutions_list.add(Point(x.toFloat(), y))
        }

        for (x in extreme) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.extreme_list.add(Point(x.toFloat(), y))
        }

        graph_extreme_n_solution.draw()

        graph.draw()
    }

    fun draw_ntb_4 (graph_info : GraphInfo) {

        var abcde = graph_info.graph_info!!

        graph.start_X_value = graph_info.startX
        graph.end_X_value= graph_info.endX

        var a = abcde[0]
        var b = abcde[1]
        var c = abcde[2]
        var d = abcde[3]
        var e = abcde[4]

        graph.processer = object : GraphDrawer.ValueProcesser {
            override fun process(x: Float): Float = a*x*x*x*x + b*x*x*x + c*x*x + d*x + e
        }

        var solutions = EquationSolver.tinh_phuong_trinh_bac_4(a.toDouble(), b.toDouble(), c.toDouble(), d.toDouble(), e.toDouble())
        var extreme = EquationExtremeFinder.tim_cuc_tri_ham_bac_4(a, b, c, d)

        for (x in solutions) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.solutions_list.add(Point(x.toFloat(), y))
        }

        for (x in extreme) {
            var y = graph.processer!!.process(x.toFloat())
            graph_extreme_n_solution.extreme_list.add(Point(x.toFloat(), y))
        }

        graph_extreme_n_solution.draw()

        graph.draw()
    }

    fun draw_htb_1 (graph_info : GraphInfo) {

        var abcd = graph_info.graph_info!!

        graph.start_X_value = graph_info.startX
        graph.end_X_value= graph_info.endX

        var a = abcd[0]
        var b = abcd[1]
        var c = abcd[2]
        var d = abcd[3]

        graph.step = BigDecimal("0.01")

        graph.processer = object : GraphDrawer.ValueProcesser {
            override fun process(x: Float): Float {
                return (a*x + b)/(c*x + d)
            }
        }

        var indentify_point : Double = (-d/c).toDouble()

        if (BigDecimal(indentify_point).rem(graph.step) != BigDecimal.ZERO) {
            Log.e("It's not ", indentify_point.toString() + " do not divive full " + graph.step.toString())

            var start_pos = Math.round(indentify_point).toDouble()

            start_pos = if (start_pos > indentify_point) start_pos - 1.0 else start_pos

            Log.e("Start pos", start_pos.toString())

            // The number nearest the indentify_point (last point in for-loop below)
            var second_num = 0.0

            var x = BigDecimal(start_pos)

            while (x.toDouble() < indentify_point) {
                second_num = x.toDouble()
                x += graph.step
            }

            graph.indentify_x_points.add(second_num)

            Log.e("Indentify number is ", graph.indentify_x_points[0].toString())
        } else {
            Log.e("It did it ?", indentify_point.toString() + " do not divive full " + graph.step.toString())

        }

        var tiem_can_ngang = AsymptoticFinder.tiem_can_ngang_huu_ti_bac_1(a, c)
        var tiem_can_dung = AsymptoticFinder.tiem_can_dung_huu_ti_bac_1(c, d)

        graph_extreme_n_solution.solutions_list.clear()
        var x = -b/a
        var y = graph.processer!!.process(x)
        graph_extreme_n_solution.solutions_list.add(Point(x, y))

        graph_extreme_n_solution.vertical_asymtotic = tiem_can_dung
        graph_extreme_n_solution.horizontal_asymtotic = tiem_can_ngang
        graph_extreme_n_solution.isDrawAsymptoticLine = true

        graph_extreme_n_solution.draw()
        graph.draw()

        graph_btn_extreme.setOnClickListener {
            graph_extreme_n_solution.switchAsymptoticLine()
            graph_btn_extreme.alpha = if (graph_btn_extreme.alpha == 1.0f) 0.75f else 1.0f
        }
    }

    fun draw_some_graph (graph_info: GraphInfo) {

        var function = Function("At(x) = " + graph_info.calc_content.processX())

        graph.processer = object : GraphDrawer.ValueProcesser {
            override fun process(x: Float): Float {
                try {
                    var expression = Expression("At($x)", function)
                    return expression.calculate().toFloat()
                } finally {

                }
            }
        }

        graph.start_X_value = graph_info.startX
        graph.end_X_value = graph_info.endX

        graph.draw()
    }

}


