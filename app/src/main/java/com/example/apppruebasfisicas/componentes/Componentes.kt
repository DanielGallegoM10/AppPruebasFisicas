package com.example.apppruebasfisicas.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CuadroTexto(texto: String, labelName: String, onValueChange: (String) -> Unit) {
    TextField(value = texto, onValueChange = { onValueChange(it) }, Modifier.padding(20.dp),
        label = { Text(text = labelName) })
}

@Composable
fun CuadroTextoPass(texto: String, labelName: String, onValueChange: (String) -> Unit) {
    TextField(
        value = texto, onValueChange = { onValueChange(it) },
        modifier = Modifier.padding(20.dp),
        label = { Text(text = labelName) },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(mask = '*')
    )
}

@Composable
fun Boton(texto: String, accionClick: () -> Unit) {
    Button(onClick = { accionClick() }) {
        Text(text = texto)
    }
}

@Composable
fun Titulo(texto: String) {
    Text(
        text = texto,
        modifier = Modifier.padding(20.dp),
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun CuadroDialogo(texto: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Aceptar")
            }
        },
        title = { Text(text = texto) },
    )
}

@Composable
fun RadioButtomSexo(texto: String, onItemSelected: (String) -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        var state by rememberSaveable { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = texto.equals("Chico"),
                enabled = true,
                onClick = {
                    onItemSelected("Chico")
                }
            )
            Text("Chico")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = texto.equals("Chica"),
                enabled = true,
                onClick = {
                    onItemSelected("Chica")
                }
            )
            Text("Chica")
        }
    }

}

@Composable
fun IconoVolver(navigateToBack: () -> Unit){
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Volver",
        modifier = Modifier.padding(10.dp)
            .size(30.dp)
            .clickable { navigateToBack() }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewComponents(){
//    Titulo("Pagina de Login")
    CuadroDialogo("Mensaje de confirmacion", {}, {})
}
