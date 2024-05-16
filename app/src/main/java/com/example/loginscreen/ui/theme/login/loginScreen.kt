package com.example.loginscreen.ui.theme.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginscreen.widget.AnimatedSnackbar
import com.example.loginscreen.widget.TextFieldCustomized
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showSnackbar =  remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    val auth = Firebase.auth

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(123, 81, 136, 255))
            .imePadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
        Text(
            text = "Login to your account",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextFieldCustomized(
            campo = email,
            texto = "Email",
            onChange = { newEmail -> email = newEmail }
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextFieldCustomized(
            campo = password,
            texto = "Password",
            onChange = { newPassword -> password = newPassword },
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors( Color(169,124,176)),
            modifier = Modifier
                .wrapContentSize(Alignment.Center),
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                navController.navigate("homeScreen")
                            } else {
                                showSnackbar.value = true
                                snackbarMessage = "Login failed"
                            }
                        }
                } else {
                    showSnackbar.value = true
                    snackbarMessage = "Please fill in all fields"
                }
            }
        ) {
            Text("Login")
        }
        if (showSnackbar.value) {
            AnimatedSnackbar(showSnackbar, snackbarMessage)
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = { navController.navigate("signInScreen") }) {
            Text(
                text = "Doesn't have a account? ",
                color = Color.White
            )
        }
    }
}