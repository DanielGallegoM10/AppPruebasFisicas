package com.example.apppruebasfisicas.login

import ThemePreferences
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apppruebasfisicas.BDD.LoginHelper
import com.example.apppruebasfisicas.ThemeSwitcher
import com.example.apppruebasfisicas.componentes.Boton
import com.example.apppruebasfisicas.componentes.CuadroDialogo
import com.example.apppruebasfisicas.componentes.CuadroTexto
import com.example.apppruebasfisicas.componentes.CuadroTextoPass
import com.example.apppruebasfisicas.componentes.DialogoCambiaContrasena
import com.example.apppruebasfisicas.componentes.Titulo
import com.example.apppruebasfisicas.entidades.LoginObj
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

@Composable
fun PantallaLogin(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit, navigateToPrincipal: (Int) -> Unit){
    Column(Modifier.fillMaxHeight().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        var textoUsuario by rememberSaveable { mutableStateOf("") }
        var textoPass by rememberSaveable { mutableStateOf("") }
        var incorrectoDatos by rememberSaveable { mutableStateOf(false) }
        var incorrectoCambioPass by rememberSaveable { mutableStateOf(false) }
        var cambiaContrasena by rememberSaveable { mutableStateOf(false) }
        var nuevaContraseña by rememberSaveable { mutableStateOf("") }

        var nombreUsuarioEncontrado by rememberSaveable { mutableStateOf("") }

        val context = LocalContext.current

        val loginHelper = LoginHelper(context)

        var usuarioEncontrado: LoginObj? = null

        var idUsuario: Int

        Spacer(modifier = Modifier.height(20.dp))
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            ThemeSwitcher(themeMode) {
                newMode ->
                onThemeChange(newMode)
                ThemePreferences.saveTheme(context, newMode)
            }
        }

        Titulo("Página de Login")
        Spacer(modifier = Modifier.weight(1f))

        CuadroTexto(textoUsuario, "Introduzca su usuario"){ textoUsuario = it }
        Spacer(modifier = Modifier.weight(1f))

        CuadroTextoPass(textoPass, "Introduzca su contraseña") { textoPass = it }
        Spacer(modifier = Modifier.weight(1f))

        Text("¿Ha olvidado su contraseña?", modifier = Modifier.clickable {
            if (textoUsuario.isEmpty()){
                incorrectoCambioPass = true
            }else{
                cambiaContrasena = true }}, color = Color.Blue)
        Spacer(modifier = Modifier.weight(1f))

        Boton("Iniciar Sesion") {
            if (textoUsuario.isEmpty() || textoPass.isEmpty()) {
                incorrectoDatos = true
            }else{
                usuarioEncontrado = loginHelper.getUsuario(textoUsuario, textoPass)
                if (usuarioEncontrado == null) {
                    incorrectoDatos = true
                }else{
                    nombreUsuarioEncontrado = usuarioEncontrado!!.usuario
                    idUsuario = usuarioEncontrado!!.id
                    navigateToPrincipal(idUsuario)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        if(incorrectoDatos){
            CuadroDialogo("Error de Inicio de Sesion","Fallo en el inicio de sesion, los datos son incorrectos", { incorrectoDatos = false }, { incorrectoDatos = false })
        }

        if (incorrectoCambioPass){
            CuadroDialogo("Error de Cambio de Contraseña", "Debe introducir un usuario para cambiar la contraseña", {incorrectoCambioPass = false}, {incorrectoCambioPass = false})
        }

        if (cambiaContrasena){
            DialogoCambiaContrasena(textoUsuario, nuevaContraseña, {cambiaContrasena = false}, { loginHelper.cambiarContrasena(textoUsuario, nuevaContraseña); cambiaContrasena = false }, { nuevaContraseña = it })
        }
    }
}