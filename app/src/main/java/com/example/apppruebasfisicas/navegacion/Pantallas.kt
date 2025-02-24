package com.example.apppruebasfisicas.navegacion

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Principal(val idUsuario: Int)

@Serializable
data class PruebasFisicas(val edad: Int)