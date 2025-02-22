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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.apppruebasfisicas.BDD.LoginHelper
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroDialogo
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.CuadroTextoPass
import com.example.apppruebasfisicas.componentes.Titulo
import com.example.apppruebasfisicas.entidades.LoginObj

@Composable
fun PantallaLogin(navigateToPrincipal: () -> Unit){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var textoUsuario by rememberSaveable { mutableStateOf("") }
        var textoPass by rememberSaveable { mutableStateOf("") }
        var correctoDatos by rememberSaveable { mutableStateOf(false) }
        var incorrectoDatos by rememberSaveable { mutableStateOf(false) }

        val context = LocalContext.current

        val loginHelper = LoginHelper(context)

        var usuarioEncontrado: LoginObj? = null

        Spacer(modifier = Modifier.weight(1f))
        Titulo("Página de Login")
        Spacer(modifier = Modifier.weight(1f))

        CuadroTexto(textoUsuario, "Introduzca su usuario"){ textoUsuario = it }
        Spacer(modifier = Modifier.weight(1f))

        CuadroTextoPass(textoPass, "Introduzca su contraseña") { textoPass = it }
        Spacer(modifier = Modifier.weight(1f))

        Boton("Iniciar Sesion") {
            if (textoUsuario.isEmpty() || textoPass.isEmpty()) {
                incorrectoDatos = true
            }else{
                usuarioEncontrado = loginHelper.getUsuario(textoUsuario, textoPass)
                if (usuarioEncontrado == null) {
                    incorrectoDatos = true
                }else{
                    correctoDatos = true
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        if(incorrectoDatos){
            CuadroDialogo("Fallo en el inicio de sesion, los datos son incorrectos", { incorrectoDatos = false }, { incorrectoDatos = false })
        }

        if (correctoDatos){
            CuadroDialogo("Datos correctos", { correctoDatos = false }, { navigateToPrincipal() })
        }

    }
}