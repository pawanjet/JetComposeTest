package com.example.jetcomposetest

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun stateManagement(){

    var state = remember {
        mutableStateOf("")
    }

    TextField(value = state.value, onValueChange = {
        state.value = it
    } )
}