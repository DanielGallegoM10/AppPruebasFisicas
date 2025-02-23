package com.example.apppruebasfisicas.entidades

import androidx.annotation.DrawableRes

data class LoginObj(
    val id: Int,
    val usuario: String,
    val pass: String
)

data class DatosObj(
    var id : Int,
    val idUsuario : Int,
    val edad : Int,
    val peso : Int,
    val altura : Int,
    val sexo : String
){
    constructor(
        idUsuario : Int,
        edad : Int,
        peso : Int,
        altura : Int,
        sexo : String
    ): this(0, idUsuario, edad, peso, altura, sexo)
}

data class PruebaFisicaObj(
    val nombre : String,
    val url : String,
    @DrawableRes val imagen : Int
)