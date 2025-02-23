package com.example.apppruebasfisicas.pruebasFisicasLista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.apppruebasfisicas.componentes.IconoVolver

@Composable
fun PruebasFisicas(navigateToBack: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.weight(1f))
            IconoVolver { navigateToBack() }
            Spacer(modifier = Modifier.weight(1f))
            //AQUI EL SEARCH VIEW
            Spacer(modifier = Modifier.weight(1f))
            //AQUI EL SWITCH
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyColumn (

        ){

        }
    }
}