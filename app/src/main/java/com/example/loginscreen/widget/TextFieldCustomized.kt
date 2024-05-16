package com.example.loginscreen.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldCustomized(campo: String, texto: String, onChange: (String) -> Unit, ) {
    OutlinedTextField(
        value = campo,
        onValueChange = { onChange(it) },
        label = { Text(texto, color = Color.White) },
        shape = RoundedCornerShape(50),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(170,124,176),
            unfocusedContainerColor = Color(170,124,176),
        )
    )
}