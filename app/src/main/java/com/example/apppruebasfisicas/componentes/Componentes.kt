package com.example.apppruebasfisicas.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Preview(showBackground = true)
@Composable
fun PreviewComponents(){
    Titulo("Pagina de Login")
}