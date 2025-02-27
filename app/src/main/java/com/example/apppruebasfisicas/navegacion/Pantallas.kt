package com.example.apppruebasfisicas.navegacion

import com.example.apppruebasfisicas.themeSwitch.ThemeMode
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Principal(val themeMode: ThemeMode, val idUsuario: Int)

@Serializable
data class PruebasFisicas(val themeMode: ThemeMode, val nombreUsuario: String, val edad: Int, val sexo: String, val idUsuario: Int)

@Serializable
data class DetallePrueba(val themeMode: ThemeMode, val nombrePrueba: String, val nombreUsuario: String, val sexo: String, val edad: Int, val idUsuario: Int)

@Serializable
data class MuestraNotas(val themeMode: ThemeMode, val idUsuario: Int)