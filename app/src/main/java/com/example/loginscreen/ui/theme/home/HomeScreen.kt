package com.example.loginscreen.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginscreen.ui.theme.LoginScreenTheme
import com.example.loginscreen.widget.FieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var tarefa by remember { mutableStateOf("") }

        TopAppBar (
            title = { Text("WorkBetter") },
            navigationIcon = { Icons.Filled.Menu  },)
        FieldValue(
            taskName = "Criar tarefa",
            onChange = { tarefa = it },
            labelText = "Digite a tarefa"
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    LoginScreenTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen()
        }
    }
}