package com.example.apppruebasfisicas.entidades

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
data class LoginObj(
    val id: Int,
    val usuario: String,
    val pass: String
)

@Serializable
data class DatosObj(
    var id : Int,
    val idUsuario : Int,
    val nombreAlumno : String,
    val edad : Int,
    val peso : Int,
    val altura : Int,
    val sexo : String
) {
    constructor(
        idUsuario : Int,
        nombreAlumno : String,
        edad : Int,
        peso : Int,
        altura : Int,
        sexo : String
    ): this(0, idUsuario, nombreAlumno, edad, peso, altura, sexo)
}

@Serializable
data class PruebaFisicaObj(
    val nombre : String,
    val url : String,
    val categoria: String,
    @DrawableRes val imagen : Int
)

@Serializable
data class NotaObj(
    val id : Int,
    val idUsuario : Int,
    val nombrePrueba : String,
    val nombreAlumno : String,
    val sexo : String,
    val edad: Int,
    val marca : String,
    val nota : String
){
    constructor(
        idUsuario : Int,
        nombrePrueba : String,
        nombreAlumno : String,
        sexo : String,
        edad: Int,
        marca : String,
        nota : String
    ): this(0, idUsuario, nombrePrueba, nombreAlumno, sexo, edad, marca, nota)
}