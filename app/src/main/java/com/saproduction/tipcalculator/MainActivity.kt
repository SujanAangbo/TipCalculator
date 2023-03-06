package com.saproduction.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saproduction.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyApp() {

    val totalCost = remember {
        mutableStateOf("")
    }

    val noOfPerson = remember {
        mutableStateOf(1)
    }

    val tipPercentage = remember {
        mutableStateOf(0.00f)
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {


        Column {
            Log.d("tag", "at header")
            ShowHeader(
                perHeadCost(
                    totalCost.value, noOfPerson.value,
                    calculateTips(totalCost.value, tipPercentage.value).toFloat()
                )
            )

            UserInput(totalCost.value, {
                if (isNumber(it)) {
                    Log.d("tag", it)
                    totalCost.value = it
                }else {
                    totalCost.value = totalCost.value
                }
            }, noOfPerson.value, {
                noOfPerson.value = it
            }, tipPercentage.value, {
                tipPercentage.value = it * 100
            })
        }


    }

}

