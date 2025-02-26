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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.ListaNotasObtenidas
import com.example.apppruebasfisicas.componentes.Titulo

@Composable
fun PantallaMuestraNotas(idUsuario: Int, navigateToBack: () -> Unit){

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconoVolver { navigateToBack() }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Titulo("Notas obtenidas por prueba y edad")
        Spacer(modifier = Modifier.height(10.dp))

        ListaNotasObtenidas(idUsuario)
    }
}