package com.example.apppruebasfisicas

import NavegacionPrincipal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apppruebasfisicas.BDD.BDDPruebasFisicas
import com.example.apppruebasfisicas.themeSwitch.ThemeMode
import com.example.apppruebasfisicas.ui.theme.AppPruebasFisicasTheme

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