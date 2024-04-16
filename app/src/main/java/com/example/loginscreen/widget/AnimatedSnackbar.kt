package com.example.loginscreen.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateFloat
import androidx.compose.runtime.MutableState

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun AnimatedSnackbar(showSnackbar: MutableState<Boolean>, snackbarMessage: String) {
    val transition = updateTransition(showSnackbar.value, label = "")
    val alpha by transition.animateFloat(label = "") { isVisible ->
        if (isVisible) 1f else 0f
    }

    AnimatedVisibility(
        visible = showSnackbar.value,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
        modifier = Modifier.alpha(alpha)
    ) {
        Snackbar(
            modifier = Modifier.width(300.dp),
        ) {
            Text(snackbarMessage)
        }
    }

    LaunchedEffect(key1 = showSnackbar.value) {
        delay(2000L)
        showSnackbar.value = false
    }
}