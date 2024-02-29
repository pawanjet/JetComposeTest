@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.example.jetcomposetest

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

@Preview
@Composable
fun TipCalculator() {

    var amount = remember {
        mutableStateOf("")
    }

    var personCounter = remember {
        mutableStateOf(1)
    }

    var tipPercentage = remember {
        mutableStateOf(0f)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        Column(modifier = Modifier.fillMaxWidth()) {

            TotalHeader(getSplitAmount(amount.value,
                tipPercentage.value,
                personCounter.value).toFloat())

            UserInputArea(amount = amount.value,
                amountChange = {
                    amount.value = it
                },
                personCounter.value,
                onAddOrRemovePerson = {
                    if (it < 0) {
                        if (personCounter.value != 1) {
                            personCounter.value--
                        }
                    } else {
                        personCounter.value++
                    }
                },
                tipPercentage.value,
                tipPercentageChange = {
                    tipPercentage.value = it
                })
        }
    }
}

@Composable
fun TotalHeader(amount: Float) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        color = colorResource(id = R.color.cyan),
        shape = RoundedCornerShape(5.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 15.dp)) {

            Text(text = "Total per person",
                style = TextStyle(color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp))

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "$ ${formatTwoDecimalPoint(amount.toString())}",
                style = TextStyle(color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UserInputArea(amount: String,
                  amountChange: (String) -> Unit,
                  personCounter: Int,
                  onAddOrRemovePerson: (Int) -> Unit,
                  tipPercentage: Float,
                  tipPercentageChange: (Float) -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp,
        color = Color.White) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)) {

            OutlinedTextField(value = amount,
                onValueChange = { amountChange.invoke(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Enter Amount") },
                keyboardOptions = KeyboardOptions(autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.let {
                        it.hide()
                    }
                }))

            if (amount.isNotBlank()) {

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Split", style = TextStyle(color = Color.Black, fontSize = 16.sp))

                    Spacer(modifier = Modifier.fillMaxWidth(.55f))

                    CustomArrowButton(imageVector = Icons.Default.KeyboardArrowDown) {

                        onAddOrRemovePerson.invoke(-1)
                    }

                    Text(text = "$personCounter",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp),
                        modifier = Modifier.padding(horizontal = 8.dp))

                    CustomArrowButton(imageVector = Icons.Default.KeyboardArrowUp) {

                        onAddOrRemovePerson.invoke(1)
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Tip", style = TextStyle(color = Color.Black, fontSize = 16.sp))

                    Spacer(modifier = Modifier.fillMaxWidth(.70f))

                    Text(text = "$ ${formatTwoDecimalPoint(getTipAmount(amount, tipPercentage))}",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp),
                        modifier = Modifier.padding(start = 12.dp))

                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(text = "${formatTwoDecimalPoint(tipPercentage.toString())} %",
                    style = TextStyle(color = Color.Black, fontSize = 13.sp))

                Spacer(modifier = Modifier.height(1.dp))

                Slider(value = tipPercentage,
                    onValueChange = {
                        tipPercentageChange.invoke(it)
                    },
                    valueRange = (0f..100f),
                    steps = 5,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth())
            }
        }
    }
}

@Composable
fun CustomArrowButton(imageVector: ImageVector, onClick: () -> Unit) {

    Card(modifier = Modifier
        .wrapContentSize()
        .padding(12.dp)
        .clickable {
            onClick.invoke()
        }, shape = CircleShape) {

        Icon(imageVector = imageVector,
            contentDescription = "Image vector arrow",
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp))
    }
}

fun getTipAmount(amount: String, tipPercentage: Float): String {

    return when {

        amount.isEmpty() -> {
            "0"
        }

        else -> {

            (amount.toFloat() * tipPercentage.div(100)).toString()
        }
    }
}

fun getSplitAmount(amount: String, tipPercentage: Float, personCounter: Int): String {

    return when {
        amount.isEmpty() -> {
            "0"
        }

        else -> {
            val totalAmount = amount.toFloat()
            val tipAmount = getTipAmount(amount, tipPercentage).toFloat()

            val perPersonAmount = (totalAmount + tipAmount).div(personCounter)

            perPersonAmount.toString()
        }
    }
}

fun formatTwoDecimalPoint(str: String): String {

    return if (str.isEmpty()) {
        ""
    } else {
        val format = DecimalFormat("##################.##")
        format.format(str.toFloat())
    }
}