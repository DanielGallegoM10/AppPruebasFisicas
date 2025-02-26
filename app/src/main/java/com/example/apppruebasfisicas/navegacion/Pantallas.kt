package com.example.apppruebasfisicas.navegacion

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Principal(val idUsuario: Int)

@Serializable
data class PruebasFisicas(val edad: Int, val sexo: String, val idUsuario: Int)

@Serializable
data class DetallePrueba(val nombrePrueba: String, val sexo: String, val edad: Int, val idUsuario: Int)

@Serializable
data class MuestraNotas(val idUsuario: Int)