package com.example.apppruebasfisicas.componentes

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.apppruebasfisicas.BDD.NotasHelper
import com.example.apppruebasfisicas.R
import com.example.apppruebasfisicas.entidades.NotaObj
import com.example.apppruebasfisicas.entidades.PruebaFisicaObj

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
        fontFamily = FontFamily.Cursive,
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
fun DialogoCambiaContrasena(
    usuario: String,
    nuevaContrasena: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onValueChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Aceptar")
            }
        },
        title = {
            Column {
                TextField(
                    value = usuario,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text(usuario) }
                )
                CuadroTextoPass(
                    nuevaContrasena,
                    "Introduzca su nueva contraseña"
                ) { onValueChange(it) }
            }
        },
    )
}

@Composable
fun RadioButtomSexo(texto: String, onItemSelected: (String) -> Unit) {
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
fun IconoVolver(navigateToBack: () -> Unit) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Volver",
        modifier = Modifier
            .padding(10.dp)
            .size(30.dp)
            .clickable { navigateToBack() }
    )
}

@Composable
fun ElementoPrueba(
    pruebaFisica: PruebaFisicaObj,
    onItemSelected: (PruebaFisicaObj) -> Unit,
) {
    val context = LocalContext.current

    Card(
        border = BorderStroke(2.dp, Color.Cyan),
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(pruebaFisica) }
    ) {
        Column {
            Image(
                painter = painterResource(id = pruebaFisica.imagen),
                contentDescription = "Imagen Prueba",
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = pruebaFisica.nombre,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Abrir enlace de la Prueba",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pruebaFisica.url))
                        context.startActivity(intent)
                    },
                fontSize = 15.sp,
                color = Color.Blue
            )
        }
    }
}

@Composable
fun ListaDePruebas(edad: Int, busqueda: String, categoriaSeleccionada: String, onItemSelected: (PruebaFisicaObj) -> Unit) {
    val listaPruebas: Map<String, List<PruebaFisicaObj>> = getPruebasFisicas(edad).filter {
        it.nombre.startsWith(busqueda, ignoreCase = true) &&  (categoriaSeleccionada.isEmpty() || it.categoria == categoriaSeleccionada)
    }.groupBy { it.categoria }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listaPruebas.forEach { (categoria, pruebas) ->
            item {
                Text(
                    text = categoria,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(pruebas) { prueba ->
                ElementoPrueba(pruebaFisica = prueba, onItemSelected = { onItemSelected(prueba) })
            }
        }
    }
}

fun getPruebasFisicas(edad: Int): List<PruebaFisicaObj> {

    if (edad <= 13) {
        return listOf(
            PruebaFisicaObj(
                "Abdominales",
                "https://www.foodspring.es/magazine/ejercicio-de-abdominales#:~:text=Los%20abdominales%20son%20uno%20de,gracias%20al%20m%C3%BAsculo%20recto%20abdominal",
                "Fuerza Muscular",
                R.drawable.abdominales
            ),
            PruebaFisicaObj(
                "Flexibilidad",
                "https://www.salud.mapfre.es/cuerpo-y-mente/deporte-y-salud/beneficios-flexibilidad/",
                "Flexibilidad",
                R.drawable.flexibilidad
            ),
            PruebaFisicaObj(
                "Test de Cooper",
                "https://universidadeuropea.com/blog/test-cooper/#:~:text=El%20test%20de%20Cooper%20es,para%20planificar%20rutinas%20de%20entrenamiento.",
                "Resistencia",
                R.drawable.test_cooper
            )
        )
    } else if (edad == 14) {
        return listOf(
            PruebaFisicaObj(
                "Abdominales",
                "https://www.foodspring.es/magazine/ejercicio-de-abdominales#:~:text=Los%20abdominales%20son%20uno%20de,gracias%20al%20m%C3%BAsculo%20recto%20abdominal",
                "Fuerza Muscular",
                R.drawable.abdominales
            ),
            PruebaFisicaObj(
                "Flexibilidad",
                "https://www.salud.mapfre.es/cuerpo-y-mente/deporte-y-salud/beneficios-flexibilidad/",
                "Flexibilidad",
                R.drawable.flexibilidad
            ),
            PruebaFisicaObj(
                "Test de Cooper",
                "https://universidadeuropea.com/blog/test-cooper/#:~:text=El%20test%20de%20Cooper%20es,para%20planificar%20rutinas%20de%20entrenamiento.",
                "Resistencia",
                R.drawable.test_cooper
            ),
            PruebaFisicaObj(
                "Velocidad 5x10",
                "https://view.genially.com/66da06ae82e07261482d3374/presentation-prueba-de-velocidad-10x5pptx#:~:text=La%20prueba%20de%20velocidad%2010x5%20consiste%20en%20correr%2010%20veces,de%20descanso%20entre%20cada%20repetici%C3%B3n.&text=Los%20baremos%20de%20la%20prueba,el%20nivel%20de%20condici%C3%B3n%20f%C3%ADsica.",
                "Velocidad",
                R.drawable.velocidad
            )
        )
    } else {
        return listOf(
            PruebaFisicaObj(
                "Abdominales",
                "https://www.foodspring.es/magazine/ejercicio-de-abdominales#:~:text=Los%20abdominales%20son%20uno%20de,gracias%20al%20m%C3%BAsculo%20recto%20abdominal",
                "Fuerza Muscular",
                R.drawable.abdominales
            ),
            PruebaFisicaObj(
                "Flexibilidad",
                "https://www.salud.mapfre.es/cuerpo-y-mente/deporte-y-salud/beneficios-flexibilidad/",
                "Flexibilidad",
                R.drawable.flexibilidad
            ),
            PruebaFisicaObj(
                "Test de Cooper",
                "https://universidadeuropea.com/blog/test-cooper/#:~:text=El%20test%20de%20Cooper%20es,para%20planificar%20rutinas%20de%20entrenamiento.",
                "Resistencia",
                R.drawable.test_cooper
            ),
            PruebaFisicaObj(
                "Velocidad 5x10",
                "https://view.genially.com/66da06ae82e07261482d3374/presentation-prueba-de-velocidad-10x5pptx#:~:text=La%20prueba%20de%20velocidad%2010x5%20consiste%20en%20correr%2010%20veces,de%20descanso%20entre%20cada%20repetici%C3%B3n.&text=Los%20baremos%20de%20la%20prueba,el%20nivel%20de%20condici%C3%B3n%20f%C3%ADsica.",
                "Velocidad",
                R.drawable.velocidad
            ),
            PruebaFisicaObj(
                "Lanzamiento de Balon",
                "https://rusterfitness.com/blog/lanzamiento-balon-medicinal-hazlo-correcto/#:~:text=El%20lanzamiento%20de%20bal%C3%B3n%20medicinal%20es%20un%20ejercicio%20funcional%20que,los%20brazos%20y%20las%20piernas.",
                "Fuerza Muscular",
                R.drawable.balon_medicinal
            )
        )
    }
}

@Composable
fun ElementoNota(nota: NotaObj){
    Card(
        border = BorderStroke(2.dp, Color.Cyan)
    ) {
        Column(
            Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(nota.nombrePrueba, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Sexo: " + nota.sexo)
            Text("Edad: " + nota.edad)
            Text("Marca: " + nota.marca)
            Text("Nota: " + nota.nota)
        }
    }
}

@Composable
fun ListaNotasObtenidas(idUsuario: Int){
    val listaNotas = getNotasObtenidas(idUsuario)
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listaNotas) { nota ->
            ElementoNota(nota)
        }
    }
}

@Composable
fun getNotasObtenidas(idUsuario: Int): List<NotaObj>{
    val context = LocalContext.current
    val notasHelper = NotasHelper(context)

    val notas = notasHelper.obtenerNotaPorUsuario(idUsuario)

    return notas
}

@Composable
fun SearchView(
    busqueda: String,
    onQueryChanged: (String) -> Unit,
    placeholder: String = "Buscar prueba"
) {
    TextField(
        value = busqueda,
        onValueChange = onQueryChanged,
        label = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        singleLine = true,
        maxLines = 1
    )
}

fun getCategorias(edad: Int): List<String> {
    if (edad <= 13) {
        return listOf("Fuerza Muscular", "Flexibilidad", "Resistencia")
    }else {
        return listOf("Fuerza Muscular", "Flexibilidad", "Resistencia", "Velocidad")
    }
}

@Composable
fun DropMenuCategorias(edad: Int, onValueChange: (String) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val categorias = getCategorias(edad)

    Column(
        Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = "Seleccione la categoria para agrupar",
            onValueChange = { onValueChange(it) },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            colors = TextFieldDefaults.colors(Color.Black)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categorias.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        expanded = false
                        onValueChange(item)
                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewComponents() {
//    Titulo("Pagina de Login")
//    CuadroDialogo("Mensaje de confirmacion", {}, {})
}
