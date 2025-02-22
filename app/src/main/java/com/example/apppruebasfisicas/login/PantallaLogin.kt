package com.example.apppruebasfisicas.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.CuadroTextoPass
import com.example.apppruebasfisicas.componentes.Titulo

@Preview(showBackground = true)
@Composable
fun PantallaLogin(){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var textoUsuario by rememberSaveable { mutableStateOf("") }
        var textoPass by rememberSaveable { mutableStateOf("") }

        Spacer(modifier = Modifier.weight(1f))
        Titulo("Página de Login")
        Spacer(modifier = Modifier.weight(1f))

        CuadroTexto(textoUsuario, "Introduzca su usuario"){ textoUsuario = it }
        Spacer(modifier = Modifier.weight(1f))

        CuadroTextoPass(textoPass, "Introduzca su contraseña") { textoPass = it }
        Spacer(modifier = Modifier.weight(1f))

        Boton("Iniciar Sesion") {

        }
        Spacer(modifier = Modifier.weight(1f))

    }
}