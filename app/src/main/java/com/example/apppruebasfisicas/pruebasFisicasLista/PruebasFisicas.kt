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
import com.example.apppruebasfisicas.ThemeSwitcher
import com.example.apppruebasfisicas.componentes.DropMenuCategorias
import com.example.apppruebasfisicas.componentes.IconoVolver
import com.example.apppruebasfisicas.componentes.ListaDePruebas
import com.example.apppruebasfisicas.componentes.SearchView
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

@Composable
fun PruebasFisicasLista(themeMode: ThemeMode, onThemeChange: (ThemeMode) -> Unit, edad: Int, navigateToBack: () -> Unit, onItemSelected: (String) -> Unit){
    var busqueda by rememberSaveable { mutableStateOf("") }
    var categoriaSeleccionada by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconoVolver { navigateToBack() }
            Spacer(modifier = Modifier.weight(1f))
            SearchView(busqueda, { busqueda = it }, "Buscar Prueba")
            Spacer(modifier = Modifier.weight(1f))
            ThemeSwitcher(themeMode) {
                    newMode ->
                onThemeChange(newMode)
                ThemePreferences.saveTheme(context, newMode)
            }
        }
        DropMenuCategorias(edad) { categoriaSeleccionada = it }
        ListaDePruebas(edad, busqueda, categoriaSeleccionada, onItemSelected = {
            onItemSelected(it.nombre)
        })
    }
}