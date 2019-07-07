package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity.GraphInfo
import kotlinx.android.synthetic.main.activity_special_graph_menu.*

class SpecialGraphMenuActivity : AppCompatActivity() {

    companion object {
        val TAM_THUC_BAC_2 = 2

        val TU_THUC_BAC_3 = 3

        val NGU_THUC_BAC_4 = 4

        val HUU_TI_BAC_1 = 5

        val GRAPH_INFO = "graph_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_graph_menu)
        addEvent()
    }

    var graph_type : Int = -1


    private fun addEvent() {


        special_graph_bac_2.setOnClickListener {
            special_graph_bac_2_form.visibility = VISIBLE
            graph_type = TAM_THUC_BAC_2
            onGraphTypeClick()
        }

        special_graph_bac_3.setOnClickListener {
            special_graph_bac_3_form.visibility = VISIBLE
            graph_type = TU_THUC_BAC_3
            onGraphTypeClick()
        }

        special_graph_bac_4.setOnClickListener {
            special_graph_bac_4_form.visibility = VISIBLE
            graph_type = NGU_THUC_BAC_4
            onGraphTypeClick()
        }

        special_graph_huu_ti_bac_1.setOnClickListener {
            special_graph_ht_bac_1_form.visibility = VISIBLE
            graph_type = HUU_TI_BAC_1
            onGraphTypeClick()
        }

        special_graph_btn_back.setOnClickListener {
            finish()
        }

        // Process graph
        special_graph_btn_process_graph.setOnClickListener {
            var intent = Intent(this, GraphDrawerActivity::class.java)

            var graph_info : FloatArray? = null

            when(graph_type) {
                TAM_THUC_BAC_2 -> {
                    graph_info = FloatArray(3)
                    graph_info[0] = bac_2_a_edt.text.toString().toFloat()
                    graph_info[1] = bac_2_b_edt.text.toString().toFloat()
                    graph_info[2] = bac_2_c_edt.text.toString().toFloat()
                }

                TU_THUC_BAC_3 -> {
                    graph_info = FloatArray(4)
                    graph_info[0] = bac_3_a_edt.text.toString().toFloat()
                    graph_info[1] = bac_3_b_edt.text.toString().toFloat()
                    graph_info[2] = bac_3_c_edt.text.toString().toFloat()
                    graph_info[3] = bac_3_d_edt.text.toString().toFloat()

                }

                NGU_THUC_BAC_4 -> {
                    graph_info = FloatArray(5)
                    graph_info[0] = bac_4_a_edt.text.toString().toFloat()
                    graph_info[1] = bac_4_b_edt.text.toString().toFloat()
                    graph_info[2] = bac_4_c_edt.text.toString().toFloat()
                    graph_info[3] = bac_4_d_edt.text.toString().toFloat()
                    graph_info[4] = bac_4_e_edt.text.toString().toFloat()
                }

                HUU_TI_BAC_1 -> {
                    graph_info = FloatArray(4)
                    graph_info[0] = ht_bac_1_a_edt.text.toString().toFloat()
                    graph_info[1] = ht_bac_1_b_edt.text.toString().toFloat()
                    graph_info[2] = ht_bac_1_c_edt.text.toString().toFloat()
                    graph_info[3] = ht_bac_1_d_edt.text.toString().toFloat()
                }

                else -> {
                    Log.e("Nothing choosen", "Yep")
                }
            }
            Log.e("GRAPH TYPE WHEN PROCESS", graph_type.toString())
            var startX = special_graph_edt_startX.text.toString().toFloat()
            var endX = special_graph_edt_endX.text.toString().toFloat()
            var graph = GraphInfo(
                startX,
                endX,
                Color.BLUE,
                "",
                graph_info,
                graph_type
            )

            intent.putExtra(GRAPH_INFO, graph)
            startActivity(intent)
        }

        // Back to choose graph menu
        special_graph_btn_back_to_graph_menu.setOnClickListener {
            when(graph_type) {
                TAM_THUC_BAC_2 -> special_graph_bac_2_form.visibility = GONE

                TU_THUC_BAC_3 -> special_graph_bac_3_form.visibility = GONE

                NGU_THUC_BAC_4 -> special_graph_bac_4_form.visibility = GONE

                HUU_TI_BAC_1 -> special_graph_ht_bac_1_form.visibility = GONE
            }

            special_graph_navigation.visibility = GONE
            special_graph_graph_menu.visibility = VISIBLE
        }
    }

    private fun onGraphTypeClick () {
        special_graph_navigation.visibility = VISIBLE
        special_graph_graph_menu.visibility = GONE
        Log.e("Graph Type", graph_type.toString())
    }
}
