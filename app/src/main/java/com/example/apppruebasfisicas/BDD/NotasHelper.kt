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

            if (nota.id > 0) {
                values.put("id", nota.id)
            }
            values.put("idUsuario", nota.idUsuario)
            values.put("nombrePrueba", nota.nombrePrueba)
            values.put("nombreAlumno", nota.nombreAlumno)
            values.put("sexo", nota.sexo)
            values.put("edad", nota.edad)
            values.put("marca", nota.marca)
            values.put("nota", nota.nota)

            notaID = db.insertOrThrow("notas", null, values).toInt()

            db.setTransactionSuccessful()

        } catch (e: Exception) {
            println(e.message)
        } finally {
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
                put("nombreAlumno", nota.nombreAlumno)
                put("sexo", nota.sexo)
                put("marca", nota.marca)
                put("edad", nota.edad)
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

    fun obtenerNotaPorUsuario(idUsuario: Int): List<NotaObj> {
        val db: SQLiteDatabase = readableDatabase
        var notas = mutableListOf<NotaObj>()

        val cursor =
            db.rawQuery("SELECT * FROM notas WHERE idUsuario = ?", arrayOf(idUsuario.toString()))

        if (cursor.moveToFirst()) {
            do {
                val nota = NotaObj(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nombrePrueba")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nombreAlumno")),
                    cursor.getString(cursor.getColumnIndexOrThrow("sexo")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                    cursor.getString(cursor.getColumnIndexOrThrow("marca")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nota"))
                )
                notas.add(nota)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return notas
    }


}