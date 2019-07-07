package com.example.drawgraphmodule


class EquationExtremeFinder {
    companion object {

        fun tim_cuc_tri_ham_bac_2 (a : Float, b : Float) : DoubleArray{
            var a2 = a*2
            var extreme = DoubleArray(1)
            extreme[0] = ((-b/a2).toDouble())
            return extreme
        }

        fun tim_cuc_tri_ham_bac_3 (a : Float, b : Float, c: Float) : DoubleArray{
            var a2 = a*3.0
            var b2 = b*2.0
            return EquationSolver.tinh_phuong_trinh_bac_2(a2, b2, c.toDouble())
        }

        fun tim_cuc_tri_ham_bac_4 (a : Float, b : Float, c : Float, d : Float) : DoubleArray{
            var a2 = a*4.0
            var b2 = b*3.0
            var c2 = c*2.0
            return EquationSolver.tinh_phuong_trinh_bac_3(a2, b2, c2, d.toDouble())
        }

    }
}