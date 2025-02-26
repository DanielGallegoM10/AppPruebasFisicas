package com.example.apppruebasfisicas.pruebasFisicasLista

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.apppruebasfisicas.componentes.DropMenuCategorias
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.ListaDePruebas
import com.example.apppruebasfisicas.componentes.SearchView

@Composable
fun PruebasFisicasLista(edad: Int, navigateToBack: () -> Unit){
    var busqueda by rememberSaveable { mutableStateOf("") }
    var categoriaSeleccionada by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.weight(1f))
            IconoVolver { navigateToBack() }
            Spacer(modifier = Modifier.weight(1f))
            SearchView(busqueda, { busqueda = it }, "Buscar Prueba")
            Spacer(modifier = Modifier.weight(1f))
            //AQUI EL SWITCH
            Spacer(modifier = Modifier.weight(1f))
        }
        DropMenuCategorias(edad) { categoriaSeleccionada = it }
        ListaDePruebas(edad, busqueda, categoriaSeleccionada, onItemSelected = {
            //Esto me llevara a la pantalla del detalle
        })
    }
}