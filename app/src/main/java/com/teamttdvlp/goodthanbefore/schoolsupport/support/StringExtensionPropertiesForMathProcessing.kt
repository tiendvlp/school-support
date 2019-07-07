package com.teamttdvlp.goodthanbefore.schoolsupport.support

import java.lang.StringBuilder


fun String.processX () : String {
    var string_builder = StringBuilder(this)
    var find_pos = 0
    var x_pos = string_builder.indexOf("x", find_pos, true)
    while (x_pos >= 0) {
        println(x_pos)
        find_pos = if (x_pos != 0 && string_builder[x_pos - 1].isDigit()) {
            string_builder.insert(x_pos, "*")
            x_pos + 2
        } else if (x_pos != length - 1 && (string_builder[x_pos + 1].isDigit())) {
            string_builder.insert(x_pos, "*")
            x_pos + 1
        } else {
            x_pos + 1
        }
        x_pos = string_builder.indexOf("x", find_pos, true)
    }

    return string_builder.toString()
}

fun String.removeAllSpace () = replace(" ", "")

fun String.processMinus () : String {
    var result = ""

    var list = split("-")

    for ((i, exp) in list.withIndex()) {
        if (exp.isNotEmpty()) {
            result = result.plus(exp)
            if (i < list.lastIndex) {
                result = if (exp[exp.length - 1].isDigit() or (exp[exp.length - 1] == 'x') or (exp.lastChar == ')'))
                    result.plus("+-")
                else
                    result.plus("-")
            }
        }
    }

    return result
}

fun String.processSQUARE () : String {

    if (contains("^2")) {

        var list : List<String> = split("^2")

        var result = ""

        for ((i, exp) in list.withIndex()) {
            if (exp.isNotEmpty()) {
                if (i < list.lastIndex) {
                    inside_loop@for (i in exp.lastIndex downTo 0) {
                        if (i == 0) {
                            if (exp[0].isDigit() or (exp[0] == '-')) {
                                result += exp.toFloat() * exp.toFloat()
                            } else {
                                var number = exp.substring(1, exp.length)
                                result += exp.removeRange(exp.length - number.length, exp.length) + Math.pow(number.toDouble(), 2.0).toString()
                            }
                        } else {
                            if (!exp[i].isDigit() and (exp[i] != '.')) {
                                var number = exp.substring(i + 1, exp.length)
                                result += exp.removeRange(exp.length - number.length, exp.length) + Math.pow(number.toDouble(), 2.0).toString()
                                break@inside_loop
                            }
                        }
                    }
                } else if (i == list.lastIndex) {
                    result += exp
                }
            }
        }

        return result
    }

    return this
}

fun String.processPow () : String {
    var content = this
    while (content.contains("^")) {
        var pos = content.indexOf("^")
        var number_need_pow = "0.0"
        var pow_num = "0.0"
        nnp@for (i in pos - 1 downTo 0) {
            if ( i != 0) {
                if (!content[i].isDigit() and (content[i] != '-') and (content[i] != '.')) {
                    number_need_pow = content.substring(i + 1, pos)
                    break@nnp
                }
            } else {
                if (content[0].isDigit() or (content[0] == '-')) {
                    number_need_pow = content.substring(0, pos)
                }
            }
        }

        pn@for (i in pos + 1..length - 1) {
            if (i != length - 1) {
                if (!content[i].isDigit() and (content[i] != '-') and (content[i] != '.')) {
                    pow_num = content.substring(pos + 1, i)
                    break@pn
                }
            } else {
                if (content[length - 1].isDigit()) {
                    pow_num = content.substring(pos + 1)
                }
            }
        }

        content = content.replaceFirst(number_need_pow+"^"+pow_num, Math.pow(number_need_pow.toDouble(), pow_num.toDouble()).toString())
    }

    return content
}

val String.lastChar : Char
    get() = this[lastIndex]