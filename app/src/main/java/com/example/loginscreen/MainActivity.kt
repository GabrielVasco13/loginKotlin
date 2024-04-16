package com.example.loginscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginscreen.ui.theme.LoginScreenTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginscreen.ui.theme.home.homeScreen
import com.example.loginscreen.ui.theme.login.LoginScreen
import com.example.loginscreen.ui.theme.signUp.SignInScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "signInScreen") {
                composable("signInScreen") { SignInScreen(navController) }
                composable("loginScreen") { LoginScreen(navController) } // Replace with your second screen
                composable("homeScreen") { homeScreen() } // Replace with your third screen
            }
        }
    }
}
