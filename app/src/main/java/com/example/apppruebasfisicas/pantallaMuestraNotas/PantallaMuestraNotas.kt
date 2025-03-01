package com.example.apppruebasfisicas.pantallaMuestraNotas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppruebasfisicas.ThemeSwitcher
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.ListaNotasObtenidas
import com.example.apppruebasfisicas.componentes.Titulo
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

//Pantalla que muestra las notas obtenidas de cada alumno.

@Composable
fun PantallaMuestraNotas(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit, idUsuario: Int, navigateToBack: () -> Unit){
    val context = LocalContext.current
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconoVolver { navigateToBack() }
            Spacer(modifier = Modifier.weight(1f))
            ThemeSwitcher(themeMode) {
                    newMode ->
                onThemeChange(newMode)
                ThemePreferences.saveTheme(context, newMode)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Titulo("Notas obtenidas por prueba y edad")
        Spacer(modifier = Modifier.height(10.dp))

        ListaNotasObtenidas(idUsuario)
    }
}