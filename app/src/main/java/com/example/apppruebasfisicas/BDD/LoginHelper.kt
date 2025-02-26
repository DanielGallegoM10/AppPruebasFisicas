package com.example.apppruebasfisicas.BDD

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.apppruebasfisicas.entidades.LoginObj

class LoginHelper(context: Context) : BDDPruebasFisicas(context) {

    fun getUsuario(nombre: String, pass: String): LoginObj? {
        val db : SQLiteDatabase = readableDatabase
        var usuario: LoginObj? = null
        val cursor = db.rawQuery("SELECT * FROM login WHERE usuario = '$nombre' AND pass = '$pass'", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombreUsuario = cursor.getString(1)
                val contraseñaUsuario = cursor.getString(2)

                usuario = LoginObj(id, nombreUsuario, contraseñaUsuario)

            }while (cursor.moveToNext())
        }
        return usuario
    }

    fun getUsuarioPorID(idUsuario: Int): LoginObj? {
        val db : SQLiteDatabase = readableDatabase
        var usuario: LoginObj? = null
        val cursor = db.rawQuery("SELECT * FROM login WHERE id = '$idUsuario'", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombreUsuario = cursor.getString(1)
                val contraseñaUsuario = cursor.getString(2)

                usuario = LoginObj(id, nombreUsuario, contraseñaUsuario)
            }while (cursor.moveToNext())
        }
        return usuario
    }

    fun cambiarContrasena(nombre: String, pass: String){
        val db : SQLiteDatabase = writableDatabase
        db.execSQL("UPDATE login SET pass = '$pass' WHERE usuario = '$nombre'")
    }

}