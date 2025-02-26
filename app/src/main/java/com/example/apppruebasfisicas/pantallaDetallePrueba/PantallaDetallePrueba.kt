package com.example.apppruebasfisicas.pantallaDetallePrueba

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppruebasfisicas.BDD.DatosHelper
import com.example.apppruebasfisicas.BDD.NotasHelper
import com.example.apppruebasfisicas.ThemeSwitcher
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroDialogo
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.Titulo
import com.example.apppruebasfisicas.entidades.NotaObj
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

@Composable
fun PantallaDetallePrueba(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit, nombrePrueba: String, edad: Int, sexo: String, idUsuario: Int, navigateToBack: () -> Unit) {
    var textoMarca by rememberSaveable { mutableStateOf("") }

    val datosHelper = DatosHelper(LocalContext.current)

    var notaCalculada by rememberSaveable { mutableStateOf("") }


    val notaHelper = NotasHelper(LocalContext.current)

    var notaObtenida: NotaObj

    var dialogoIncorrecto by rememberSaveable { mutableStateOf(false) }
    var dialogoCorrecto by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
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
        Spacer(modifier = Modifier.weight(1f))
        Titulo("Prueba de: $nombrePrueba")
        Spacer(modifier = Modifier.weight(1f))

        CuadroTexto(textoMarca, "Dime la marca") { textoMarca = it }
        Spacer(modifier = Modifier.weight(1f))

        Boton("Calcular Nota") {
            notaCalculada =
                calcularNota(
                    nombrePrueba,
                    textoMarca.toFloatOrNull() ?: 0f,
                    edad,
                    sexo
                )
            if (textoMarca.isEmpty() || notaCalculada == "N/A") {
                dialogoIncorrecto = true
            } else {
                dialogoCorrecto = true
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        if (dialogoIncorrecto) {
            CuadroDialogo(
                "La marca introducida no coincide con el Baremo EuroFit",
                { dialogoIncorrecto = false },
                { dialogoIncorrecto = false })
        }
        if (dialogoCorrecto) {
            CuadroDialogo(
                "La nota obtenida es: $notaCalculada",
                { dialogoCorrecto = false },
                {
                    notaObtenida = NotaObj(idUsuario, nombrePrueba, sexo, edad, textoMarca, notaCalculada)
                    notaHelper.guardarNota(notaObtenida)
                    dialogoCorrecto = false })
        }

    }
}
