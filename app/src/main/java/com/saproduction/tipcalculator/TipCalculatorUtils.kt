package com.saproduction.tipcalculator

import android.util.Log
import java.text.DecimalFormat

// function to avoid unnecessary app crash.
fun isNumber(str: String): Boolean {

    if(str.length >= 10) {
        return false
    }

    if(str.isEmpty()) {
        return true
    }

    var char: Int = str[str.length - 1].code

    Log.d("tag1", "${char - '0'.code}")

    var num = char - '0'.code
    return num in 0..9

//    return when {
//        str.length >= 10 -> true
//        str.contains(",") -> true
//        str.contains(".") -> true
//        str.contains("-") -> true
//
//        else -> false
//    }
}

fun formatDecimal(num: String): String {

    if (num.isEmpty()) {
        return ""
    }

    var format = DecimalFormat("##########################.##")
    return format.format(num.toDouble()).toString()


}

fun perHeadCost(totalCost: String, noOfPerson: Int, tipAmount: Float): String {
    Log.d("tag", "before OverallCost")

    if (totalCost.isEmpty()) {
        return "0"
    }

    var overallCost = totalCost.toLong() + tipAmount
    Log.d("tag", "after OverallCost : $overallCost")
    return (overallCost / noOfPerson).toString()

}

fun calculateTips(totalCost: String, tipPercentage: Float): String {

    Log.d("tag", "start of calculateTips")

    return if (totalCost.isEmpty()) {
        Log.d("tag", "Inside totalcost.isEmpty() Method")
        "0"
    } else {
        val result = (totalCost.toLong() * tipPercentage.div(100)).toString()
        Log.d("tag", "result : $result")
        result
    }

}