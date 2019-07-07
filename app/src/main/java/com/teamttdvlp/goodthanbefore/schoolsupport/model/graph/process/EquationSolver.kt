package com.example.drawgraphmodule
import java.lang.Exception

class EquationSolver {

    companion object {
        fun tinh_phuong_trinh_bac_4 (t : Double, a1 : Double, b1 : Double, c1 : Double, d1 : Double) : DoubleArray {
            val a : Double = a1/t
            val b : Double = b1/t
            val c : Double = c1/t
            val d : Double = d1/t

            val offset = a/4.0

            val m : Double
            val n : Double
            val p : Double
            var q = 0.0

            m = b - 3*(a*a)/8
            n = (a*a*a)/8 - (a*b)/2 + c
            p = (a*a*b)/16  + d - (3)*(a*a*a*a)/256 - (a*c)/4

            println("m = $m")
            println("n = $n")
            println("p = $p")

            val m_values = tinh_phuong_trinh_bac_3(8.0, -4.0 * m, -8.0 * p, 4.0 * m * p - n*n)

            var s = 1.0

            for (m_val in m_values) {
                val u = Math.abs(Math.round(m_val) - m_val)
                if (u < s) {
                    s = u
                    q = m_val
                }
            }

            var solutionArrayList = ArrayList<Double> ()

            var solutions_count = 0
            try {
                val x1 = 0.5 * (Math.sqrt(2*q - m) + Math.sqrt(-(2*n)/Math.sqrt(2*q - m)  - 2*q - m )) - offset
                if (!x1.isNaN()) {
                    solutionArrayList.add(x1)
                    solutions_count++
                }
            } catch (ex : Exception) {}

            try {
                val x2 = 0.5 * (Math.sqrt(2*q - m) - Math.sqrt(-(2*n)/Math.sqrt(2*q - m)  - 2*q - m )) - offset
                if (!x2.isNaN()) {
                    solutionArrayList.add(x2)
                    solutions_count++
                }
            } catch (ex : Exception) {}

            try {
                val x3 = 0.5 * (-(Math.sqrt(2*q - m)) + Math.sqrt((2*n)/Math.sqrt(2*q - m)  - 2*q - m )) - offset
                if (!x3.isNaN()) {
                    solutionArrayList.add(x3)
                    solutions_count++
                }
            } catch (ex : Exception) {}

            try {
                val x4 = 0.5 * ((-Math.sqrt(2*q - m)) - Math.sqrt((2*n)/Math.sqrt(2*q - m)  - 2*q - m )) - offset
                if (!x4.isNaN()) {
                    solutionArrayList.add(x4)
                    solutions_count++
                }
            } catch (ex : Exception) {}

            var solutions = DoubleArray(solutions_count)
            for ((i,x) in solutionArrayList.withIndex()) {
                solutions[i] = x
            }

            return solutions
        }

        fun tinh_phuong_trinh_bac_3(a : Double, b : Double, c : Double, d : Double) : DoubleArray {

            val delta = (b*b) - 3*a*c

            val k : Double = ((9*a*b*c) - 2*(b*b*b) - 27*(a*a)*d) / (2*Math.sqrt(Math.pow(Math.abs(delta), 3.0)))

            if ((a != 0.0) and (c != 0.0) and (d == 0.0)) {

                if (((a - b + c) == 0.0) ) {

                    if (Math.abs(a) == Math.abs(c)) {
                        val solutions = DoubleArray(2)
                        solutions[0] = -1.0
                        solutions[1] = 0.0

                        return solutions
                    } else {
                        val solutions = DoubleArray(3)

                        solutions[0] = -1.0
                        solutions[1] = -c/a
                        solutions[2] = 0.0

                        return solutions
                    }

                } else if ((a + b + c) == 0.0) {
                    return if (Math.abs(a) == Math.abs(c)) {
                        val solutions = DoubleArray(2)
                        solutions[0] = 1.0
                        solutions[1] = 0.0

                        return solutions
                    } else {
                        val solutions = DoubleArray(3)
                        solutions[0] = 1.0
                        solutions[1] = c/a
                        solutions[2] = 0.0

                        return solutions
                    }
                }
            }
            else if (delta > 0) {
                if (Math.abs(k) < 1) {

                    val x1 = (2 * Math.sqrt(delta) * Math.cos(Math.acos(k)/3) - b)/(3*a)
                    val x2 = (2 * Math.sqrt(delta) * Math.cos(Math.acos(k)/3 - 2*Math.PI/3) - b)/(3*a)
                    val x3 = (2 * Math.sqrt(delta) * Math.cos(Math.acos(k)/3 + 2*Math.PI/3) - b)/(3*a)

                    val solutions = DoubleArray(3)

                    solutions[0] = x1
                    solutions[1] = x2
                    solutions[2] = x3

                    return solutions

                } else {
                    val first_num_gr = Math.sqrt(delta) * Math.abs(k) / (3*a*k)
                    val abs_k = Math.abs(k)
                    val sqrt_k_str_1 = Math.sqrt((k*k) - 1)
                    val x = first_num_gr * (Math.cbrt(abs_k - sqrt_k_str_1) + Math.cbrt(abs_k + sqrt_k_str_1)) - b/(3*a)
                    val solution = DoubleArray(1)
                    solution[0] = x
                    return solution
                }
            } else if (delta == 0.0) {
                val delta_2 = Math.pow(b, 3.0) - 27*(a*a)*d

                val x: Double

                x = if (delta_2 == 0.0) {
                    -b/(3*a)
                } else {
                    (-b + Math.cbrt(b*b*b - 27*(a*a)*d)) / (3*a)
                }

                val solution = DoubleArray(1)

                solution[0] = x

                return solution

            } else {

                val first_num_gr = Math.sqrt(Math.abs(delta)) / (3*a)
                val sqrt_k_pls_1 = Math.sqrt((k*k) + 1)
                val x = first_num_gr * (Math.cbrt(k + sqrt_k_pls_1) + Math.cbrt(k - sqrt_k_pls_1)) - b/(3*a)
                val solution = DoubleArray(1)
                solution[0] = x

                return solution
            }

            return DoubleArray(0)
        }

        fun tinh_phuong_trinh_bac_2 (a : Double, b : Double, c : Double) : DoubleArray {

            var delta = b*b - 4*a*c

            if (delta < 0) return DoubleArray(0)

            else if (delta == 0.0) {
                var only_solution = DoubleArray(1)
                only_solution[0] = -b/(2*a)
                return only_solution
            } else {
                var two_solutions = DoubleArray(2)
                var x1 = (-b + Math.sqrt(delta)) / (2*a)
                var x2 = (-b - Math.sqrt(delta)) / (2*a)
                two_solutions [0] = x1
                two_solutions [1] = x2
                return two_solutions
            }
        }
    }


}
