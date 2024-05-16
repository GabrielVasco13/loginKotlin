package com.example.loginscreen.ui.theme.signUp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.loginscreen.ui.theme.roxo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginscreen.widget.AnimatedSnackbar
import com.example.loginscreen.widget.TextFieldCustomized
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val showSnackbar = remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    val auth = Firebase.auth

    Column (
        Modifier
            .fillMaxSize()
            .background(Color(123, 81, 136, 255))
            .imePadding()
            .navigationBarsPadding(), Arrangement.Center, Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome new user!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Sign in your account",color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldCustomized(
            campo = username,
            texto = "Username",
            onChange = { newUsername -> username = newUsername }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldCustomized(
            campo = email,
            texto = "Email",
            onChange = { newEmail -> email = newEmail }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldCustomized(
            campo = password,
            texto = "Password",
            onChange = { newPassword -> password = newPassword }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors( Color(169,124,176)),
            modifier = Modifier
                .wrapContentSize(Alignment.Center),
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{ task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                navController.navigate("loginScreen")
                            } else {
                                showSnackbar.value = true
                                snackbarMessage = "Account creation failed"
                                println(task.exception)
                            }
                        }
                } else {
                    showSnackbar.value = true
                    snackbarMessage = "Please fill in all fields"
                }
            }
        ) {
            Text("Sing In", color = Color.White)
        }
        if (showSnackbar.value) {
            AnimatedSnackbar(showSnackbar, snackbarMessage)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("loginScreen") }) {
            Text(text = "Already have an account?", color = Color.White)
        }
    }
}