package com.saproduction.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saproduction.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.DecimalFormat

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

    var totalCost = remember {
        mutableStateOf("")
    }

    var noOfPerson = remember {
        mutableStateOf(1)
    }

    var tipPercentage = remember {
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

