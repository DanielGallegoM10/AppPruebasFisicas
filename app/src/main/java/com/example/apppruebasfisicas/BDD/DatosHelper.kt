package com.example.apppruebasfisicas.BDD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.apppruebasfisicas.entidades.DatosObj

class DatosHelper(context: Context) : BDDPruebasFisicas(context){

    fun guardarDatos(datos: DatosObj): Int {
        val db: SQLiteDatabase = writableDatabase

        var datosID = -1

        db.beginTransaction()
        try{
            val values: ContentValues = ContentValues()

            if (datos.id > 0){
                values.put("id", datos.id)
            }
            values.put("idUsuario", datos.idUsuario)
            values.put("edad", datos.edad)
            values.put("peso", datos.peso)
            values.put("altura", datos.altura)
            values.put("sexo", datos.sexo)

            datosID = db.insertOrThrow("datos", null, values).toInt()

            db.setTransactionSuccessful()

        }catch (e: Exception){
            println(e.message)
        }finally {
            db.endTransaction()
        }
        return datosID
    }

    fun actualizarDatos(datos: DatosObj): Int {
        if (datos.id <= 0){
            return -1
        }

        val db: SQLiteDatabase = writableDatabase
        var datosID = -1
        db.beginTransaction()

        try {
            val values = ContentValues().apply {
                put("idUsuario", datos.idUsuario)
                put("edad", datos.edad)
                put("peso", datos.peso)
                put("altura", datos.altura)
                put("sexo", datos.sexo)
            }

            val rows = db.update("datos", values, "id=?", arrayOf(datos.id.toString()))
            if (rows > 0) {
                db.setTransactionSuccessful()
                datosID = datos.id
            }

        } catch (e: Exception) {
            println("Error al actualizar datos: ${e.message}")
        } finally {
            db.endTransaction()
        }

        return datosID
    }

    fun obtenerDatosPorUsuario(idUsuario: Int): DatosObj? {
        val db: SQLiteDatabase = readableDatabase
        var datosObj: DatosObj? = null

        val cursor = db.rawQuery("SELECT * FROM datos WHERE idUsuario = ?", arrayOf(idUsuario.toString()))

        if (cursor.moveToFirst()) {
            datosObj = DatosObj(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")),
                cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                cursor.getInt(cursor.getColumnIndexOrThrow("peso")),
                cursor.getInt(cursor.getColumnIndexOrThrow("altura")),
                cursor.getString(cursor.getColumnIndexOrThrow("sexo"))
            )
        }
        cursor.close()
        return datosObj
    }



}