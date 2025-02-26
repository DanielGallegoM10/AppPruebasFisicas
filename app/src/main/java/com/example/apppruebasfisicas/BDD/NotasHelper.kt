package com.example.apppruebasfisicas.BDD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apppruebasfisicas.entidades.NotaObj

class NotasHelper(context: Context) : BDDPruebasFisicas(context) {

    fun guardarNota(nota: NotaObj): Int {
        val db: SQLiteDatabase = writableDatabase

        var notaID = -1

        db.beginTransaction()

        try {
            val values: ContentValues = ContentValues()

            if (nota.id > 0){
                values.put("id", nota.id)
            }
            values.put("idUsuario", nota.idUsuario)
            values.put("nombrePrueba", nota.nombrePrueba)
            values.put("edad", nota.edad)
            values.put("marca", nota.marca)
            values.put("nota", nota.nota)

            notaID = db.insertOrThrow("notas", null, values).toInt()

            db.setTransactionSuccessful()

        }catch (e: Exception){
            println(e.message)
        }finally {
            db.endTransaction()
        }
        return notaID
    }

    fun actualizarNota(nota: NotaObj): Int {
        if (nota.id <= 0) {
            return -1
        }

        val db: SQLiteDatabase = writableDatabase

        var notaID = -1

        db.beginTransaction()

        try {
            val values = ContentValues().apply {
                put("idUsuario", nota.idUsuario)
                put("nombrePrueba", nota.nombrePrueba)
                put("marca", nota.marca)
                put("nota", nota.edad)
                put("nota", nota.nota)
            }

            val rows = db.update("notas", values, "id = ?", arrayOf(nota.id.toString()))

            if (rows > 0) {
                db.setTransactionSuccessful()
                notaID = nota.id
            }

        } catch (e: Exception) {
            println(e.message)
        } finally {
            db.endTransaction()
        }
        return notaID
    }

    fun obtenerNotaPorUsuario(idUsuario: Int): NotaObj? {
        val db: SQLiteDatabase = readableDatabase
        var notas: NotaObj? = null

        val cursor = db.rawQuery("SELECT * FROM notas WHERE idUsuario = ?", arrayOf(idUsuario.toString()))

        if (cursor.moveToFirst()) {
            notas = NotaObj(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombrePrueba")),
                cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                cursor.getString(cursor.getColumnIndexOrThrow("marca")),
                cursor.getString(cursor.getColumnIndexOrThrow("nota"))
            )
        }
        cursor.close()
        return notas
    }


}