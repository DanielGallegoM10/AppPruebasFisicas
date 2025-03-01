package com.example.apppruebasfisicas.componentes

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Brightness2
import androidx.compose.material.icons.rounded.Brightness5
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.apppruebasfisicas.BDD.NotasHelper
import com.example.apppruebasfisicas.R
import com.example.apppruebasfisicas.entidades.NotaObj
import com.example.apppruebasfisicas.entidades.PruebaFisicaObj
import com.example.apppruebasfisicas.themeSwitch.ThemeMode

//Cuadro de texto, utilizado para poner nombres de usuario u otros datos como edad, peso, marca, etc.
@Composable
fun CuadroTexto(
    texto: String,
    labelName: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = texto,
        onValueChange = onValueChange,
        label = { Text(text = labelName) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

//Cuadro de texto para contraseñas, solo utilizado en la pantalla de login
@Composable
fun CuadroTextoPass(
    texto: String,
    labelName: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = texto,
        onValueChange = onValueChange,
        label = { Text(text = labelName) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = "Ver contraseña")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

//Boton utilizado en toda la app, sencillo, solo contiene texto y accion al clicar
@Composable
fun Boton(
    texto: String,
    accionClick: () -> Unit,
) {
    Button(
        onClick = { accionClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .height(45.dp)
            .shadow(6.dp, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = texto,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//Titulo utilizado en toda la app, solo contiene texto
@Composable
fun Titulo(texto: String) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    val textColor = MaterialTheme.colorScheme.onPrimary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            color = textColor,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.3f),
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            )
        )
    }
}

//Cuadro de dialogo utilizado en toda la app, sobretodo para alertas de error o calculos(IMC y notas)
@Composable
fun CuadroDialogo(
    titulo: String,
    mensaje: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        title = { Text(text = titulo, fontWeight = FontWeight.Bold) },
        text = { Text(text = mensaje) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Aceptar", color = MaterialTheme.colorScheme.primary)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    )
}

//Alert Dialog, funcionalidad principal y unica, el cambio de contraseña, usado solamente en el login
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
        title = { Text("Cambio de Contraseña", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = usuario,
                    onValueChange = {},
                    label = { Text("Usuario") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )

                CuadroTextoPass(
                    texto = nuevaContrasena,
                    labelName = "Nueva Contraseña",
                    onValueChange = onValueChange
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() },
                enabled = nuevaContrasena.isNotBlank()
            ) {
                Text("Aceptar", color = MaterialTheme.colorScheme.primary)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    )
}

//Componente encargado de permitir la selección del sexo del alumno
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
            Text("Chico", color = MaterialTheme.colorScheme.onBackground)
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
            Text("Chica", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

//Icono de volver, utilizado en toda la app para volver a la pantalla anterior
@Composable
fun IconoVolver(navigateToBack: () -> Unit) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Volver",
        modifier = Modifier
            .padding(start = 5.dp)
            .size(30.dp)
            .clickable { navigateToBack() },
        tint = MaterialTheme.colorScheme.onBackground
    )
}

//Elemento de la prueba fisica, se utiliza el componente Card.
@Composable
fun ElementoPrueba(
    pruebaFisica: PruebaFisicaObj,
    onItemSelected: (PruebaFisicaObj) -> Unit,
) {
    val context = LocalContext.current

    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .width(220.dp)
            .clickable { onItemSelected(pruebaFisica) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pruebaFisica.imagen),
                contentDescription = "Imagen Prueba",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pruebaFisica.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Abrir enlace de la Prueba",
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pruebaFisica.url))
                        context.startActivity(intent)
                    }
                    .padding(4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

//Recycler View de la lista de pruebas, se destaca la funcionalidad de filtrar las pruebas por categoria
//y por nombre de prueba.
@Composable
fun ListaDePruebas(
    edad: Int,
    busqueda: String,
    categoriaSeleccionada: String,
    onItemSelected: (PruebaFisicaObj) -> Unit
) {
    val listaPruebas: Map<String, List<PruebaFisicaObj>> = getPruebasFisicas(edad).filter {
        it.nombre.startsWith(busqueda, ignoreCase = true) &&
                (categoriaSeleccionada.isEmpty() || it.categoria == categoriaSeleccionada)
    }.groupBy { it.categoria }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        listaPruebas.forEach { (categoria, pruebas) ->
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = categoria,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(pruebas) { prueba ->
                ElementoPrueba(pruebaFisica = prueba, onItemSelected = { onItemSelected(prueba) })
            }
            item { HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline) }
        }
    }
}

//Funcion que devielve una lista de pruebas, dependiendo de la edad del alumno
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

//Elemento de la nota, se utiliza el componente Card. Se han agregado el uso de "emoticonos" para
//hacer más visual los textos de la Card.
@Composable
fun ElementoNota(nota: NotaObj) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(6.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = nota.nombrePrueba,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Text(text = "👨‍🎓 Alumno: ${nota.nombreAlumno}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "⚥ Sexo: ${nota.sexo}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "📅 Edad: ${nota.edad}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "🏆 Marca: ${nota.marca} → Nota: ${nota.nota}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

//Recycler View de las notas
@Composable
fun ListaNotasObtenidas(idUsuario: Int){
    val listaNotas = getNotasObtenidas(idUsuario)
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        items(listaNotas) { nota ->
            ElementoNota(nota)
        }
    }
}

//Funcion que devuelve una lista de notas, dependiendo del id del usuario
//Se utiliza el metodo obtenerNotaPorUsuario de la clase NotasHelper
@Composable
fun getNotasObtenidas(idUsuario: Int): List<NotaObj>{
    val context = LocalContext.current
    val notasHelper = NotasHelper(context)

    val notas = notasHelper.obtenerNotaPorUsuario(idUsuario)

    return notas
}

//Barra de busqueda, se utiliza el componente OutlinedTextField, se le pasan como parametros,
//un texto de busqueda, una lambda que se ejecuta al cambiar el texto de busqueda y un texto de ayuda
@Composable
fun SearchView(
    busqueda: String,
    onQueryChanged: (String) -> Unit,
    placeholder: String = "Buscar prueba"
) {
    OutlinedTextField(
        value = busqueda,
        onValueChange = onQueryChanged,
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar") },
        trailingIcon = {
            if (busqueda.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar busqueda")
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .padding(8.dp)
    )
}

//Funcion que devuelve una lista de categorias, dependiendo de la edad del alumno
fun getCategorias(edad: Int): List<String> {
    if (edad <= 13) {
        return listOf("Fuerza Muscular", "Flexibilidad", "Resistencia")
    }else {
        return listOf("Fuerza Muscular", "Flexibilidad", "Resistencia", "Velocidad")
    }
}

//Componente que permite seleccionar una categoria de la lista de categorias,
//al seleccionar esta categoria se filtra la lista de pruebas
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DropMenuCategorias(edad: Int, onValueChange: (String) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val categorias = getCategorias(edad)
    var selectedText by rememberSaveable { mutableStateOf("Seleccione una categoría") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir menu"
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                disabledBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categorias.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        onValueChange(item)
                    }
                )
            }
        }
    }
}