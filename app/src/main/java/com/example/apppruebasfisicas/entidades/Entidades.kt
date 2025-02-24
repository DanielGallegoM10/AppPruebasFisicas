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

@Parcelize
@Serializable
data class DatosObj(
    var id : Int,
    val idUsuario : Int,
    val edad : Int,
    val peso : Int,
    val altura : Int,
    val sexo : String
): Parcelable {
    constructor(
        idUsuario : Int,
        edad : Int,
        peso : Int,
        altura : Int,
        sexo : String
    ): this(0, idUsuario, edad, peso, altura, sexo)
}

@Parcelize
@Serializable
data class PruebaFisicaObj(
    val nombre : String,
    val url : String,
    @DrawableRes val imagen : Int
): Parcelable