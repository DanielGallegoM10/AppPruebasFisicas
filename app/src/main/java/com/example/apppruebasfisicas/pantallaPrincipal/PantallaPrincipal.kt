package com.example.apppruebasfisicas.pantallaPrincipal

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroDialogo
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.RadioButtomSexo

@Preview(showBackground = true)
@Composable
fun PantallaPrincipal() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var dialogoImc by rememberSaveable { mutableStateOf(false) }
        var edad by rememberSaveable { mutableStateOf("") }
        var peso by rememberSaveable { mutableStateOf("") }
        var altura by rememberSaveable { mutableStateOf("") }
        var textoSexo by rememberSaveable { mutableStateOf("") }
        var imc by rememberSaveable { mutableStateOf("") }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Boton("Ver Notas") { }
            Spacer(modifier = Modifier.weight(1f))
            Boton("Calcular IMC") { dialogoImc = true }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
        CuadroTexto(edad, "Introduzca su edad") { edad = it }

        Spacer(modifier = Modifier.weight(1f))
        CuadroTexto(peso, "Introduzca su peso") { peso = it }

        Spacer(modifier = Modifier.weight(1f))
        CuadroTexto(altura, "Introduzca su altura") { altura = it }

        Spacer(modifier = Modifier.weight(1f))
        RadioButtomSexo(textoSexo) { textoSexo = it }


        Spacer(modifier = Modifier.weight(1f))
        Boton("Continuar") {  }
        Spacer(modifier = Modifier.weight(1f))

        imc = peso.toInt() / (altura.toInt() * altura.toInt())

        if (dialogoImc) {
            CuadroDialogo("Su IMC es de: $imc", { dialogoImc = false }, { dialogoImc = false })
        }


    }


}
