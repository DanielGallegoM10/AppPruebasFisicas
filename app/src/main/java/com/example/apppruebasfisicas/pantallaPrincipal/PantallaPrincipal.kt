package com.example.apppruebasfisicas.pantallaPrincipal

import android.widget.Button
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.apppruebasfisicas.BDD.DatosHelper
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroDialogo
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.RadioButtomSexo
import com.example.apppruebasfisicas.entidades.DatosObj

//@Preview(showBackground = true)
@Composable
fun PantallaPrincipal(idUsuario: Int, navigateToBack: () -> Unit, navigateToPruebasFisicas: (Int) -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconoVolver { navigateToBack() }
        }
        Column(
            Modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val datosHelper: DatosHelper = DatosHelper(LocalContext.current)
            var dialogoImc by rememberSaveable { mutableStateOf(false) }
            var dialogoErrorDatos by rememberSaveable { mutableStateOf(false) }
            var edad by rememberSaveable { mutableStateOf("") }
            var peso by rememberSaveable { mutableStateOf("") }
            var altura by rememberSaveable { mutableStateOf("") }
            var textoSexo by rememberSaveable { mutableStateOf("") }
            var imcTexto by rememberSaveable { mutableStateOf("") }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Boton("Ver Notas") { }
                Spacer(modifier = Modifier.weight(1f))
                Boton("Calcular IMC") {
                    val pesoValor = peso.toFloatOrNull()
                    val alturaValor = altura.toFloatOrNull()
                    if (pesoValor != null && alturaValor != null && pesoValor > 0 && alturaValor > 0) {
                        val alturaEnMetros = alturaValor / 100
                        val imc = pesoValor / (alturaEnMetros * alturaEnMetros)
                        imcTexto = "Su IMC es de: %.2f".format(imc)
                        dialogoImc = true
                    } else {
                        imcTexto = "Introduzca valores validos"
                        dialogoImc = true
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text("$idUsuario")
            Spacer(modifier = Modifier.weight(1f))
            CuadroTexto(edad, "Introduzca su edad") { edad = it }

            Spacer(modifier = Modifier.weight(1f))
            CuadroTexto(peso, "Introduzca su peso") { peso = it }

            Spacer(modifier = Modifier.weight(1f))
            CuadroTexto(altura, "Introduzca su altura") { altura = it }

            Spacer(modifier = Modifier.weight(1f))
            RadioButtomSexo(textoSexo) { textoSexo = it }

            Spacer(modifier = Modifier.weight(1f))
            Boton("Continuar") {
                if (edad.isEmpty() || peso.isEmpty() || altura.isEmpty() || textoSexo.isEmpty()) {
                    dialogoErrorDatos = true
                } else {
                    val datosObj = DatosObj(idUsuario, edad.toInt(), peso.toInt(), altura.toInt(), textoSexo)

                    val datosExistentes = datosHelper.obtenerDatosPorUsuario(idUsuario)

                    if (datosExistentes != null) {
                        datosObj.id = datosExistentes.id
                        datosHelper.actualizarDatos(datosObj)
                    } else {
                        datosHelper.guardarDatos(datosObj)
                    }
                    navigateToPruebasFisicas(edad.toInt())
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            if (dialogoImc) {
                CuadroDialogo(imcTexto, { dialogoImc = false }, { dialogoImc = false })
            }
            if (dialogoErrorDatos) {
                CuadroDialogo("ERROR, alguno de los datos no ha sido introducido", { dialogoErrorDatos = false }, { dialogoErrorDatos = false })
            }
        }
    }
}
