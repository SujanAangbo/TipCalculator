package com.saproduction.tipcalculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ShowHeader(cost: String) { // this will show the cost per person

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp), color = Color(R.color.header_bg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "Total Per Person", style = TextStyle(
                    color = Color.Black, fontSize = 25.sp, fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            Text(
                text = "$${
                    formatDecimal(cost)
                }", style = TextStyle(
                    color = Color.Black, fontSize = 35.sp, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInput(
    totalAmount: String,
    totalAmountChange: (String) -> Unit,
    noOfPerson: Int = 1,
    noOfPersonChanged: (Int) -> Unit,
    tipPercentage: Float,
    sliderValueChanged: (Float) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    Card(modifier = Modifier.padding(8.dp), elevation = 10.dp) {
        Column(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(
                value = totalAmount,
                placeholder = { Text(text = "Enter Total Cost") },
                onValueChange = { totalAmountChange.invoke(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                shape = RoundedCornerShape(10.dp)
            )

            if(!totalAmount.isEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(.05f))
                    Text(text = "Split", style = TextStyle(fontSize = 16.sp))
                    Spacer(modifier = Modifier.fillMaxWidth(.50f))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        ShowArrowButton(R.drawable.up_arrow,
                            "Up Arrow",
                            person = noOfPerson,
                            personChange = { noOfPersonChanged.invoke(it) })
                        Text(text = "$noOfPerson", modifier = Modifier.padding(horizontal = 5.dp))
                        ShowArrowButton(R.drawable.down_arrow, "Down Arrow", noOfPerson) {
                            noOfPersonChanged.invoke(
                                it
                            )
                        }
                    }


                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.fillMaxWidth(.05f))
                    Text(text = "Tips", style = TextStyle(fontSize = 16.sp))
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                    Text(
                        text = formatDecimal(calculateTips(totalAmount, tipPercentage)),
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                Text(
                    text = "${formatDecimal(tipPercentage.toString())} %",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp)
                )

                Slider(value = tipPercentage / 100, onValueChange = {
                    sliderValueChanged.invoke(it)
                }, valueRange = 0.00f..1.00f, steps = 5)

            }
        }

    }
}



@Composable
fun ShowArrowButton(resource: Int, description: String, person: Int, personChange: (Int) -> Unit) {

    Icon(painter = painterResource(resource),
        contentDescription = description,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clickable {
                if (resource == R.drawable.up_arrow) {
                    personChange.invoke(person + 1)
                } else {
                    if (person > 1) {
                        personChange.invoke(person - 1)
                    }
                }
            })
}
