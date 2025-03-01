package com.example.apppruebasfisicas

import NavegacionPrincipal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apppruebasfisicas.BDD.BDDPruebasFisicas
import com.example.apppruebasfisicas.navegacion.Login
import com.example.apppruebasfisicas.themeSwitch.ThemeMode
import com.example.apppruebasfisicas.ui.theme.AppPruebasFisicasTheme
import kotlinx.coroutines.delay

//Clase Main, en esta clase se aplica la funcionalidad del cambio de tema y tambien se encuentra
//la pantalla del splash inicial.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current

            var themeMode by rememberSaveable { mutableStateOf(ThemePreferences.getTheme(this)) }
            AppPruebasFisicasTheme (themeMode = themeMode) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    NavegacionPrincipal(themeMode) { newMode ->
                        themeMode = newMode
                        ThemePreferences.saveTheme(context, newMode)
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcher(currentMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit) {
    val isDarkMode = currentMode == ThemeMode.DARK

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 5.dp)
    ) {

        Switch(
            checked = isDarkMode,
            onCheckedChange = { isChecked ->
                onThemeChange(if (isChecked) ThemeMode.DARK else ThemeMode.LIGHT)
            }
        )
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(1f, animationSpec = tween(1000, easing = FastOutSlowInEasing))
        delay(2000)
        navController.navigate(Login)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondogimnasioapp),
            contentDescription = "Imagen de fondo",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        Image(
            painter = painterResource(id = R.drawable.iconoapp),
            contentDescription = "Icono de la App",
            modifier = Modifier
                .size(130.dp)
                .scale(scale.value)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}


