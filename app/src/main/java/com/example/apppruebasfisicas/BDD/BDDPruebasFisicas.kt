package com.example.apppruebasfisicas.BDD

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class BDDPruebasFisicas(context: Context) :
    SQLiteOpenHelper(context, "bddPruebasFisicas", null, 1) {

    private val crearTablaLogin =
        "CREATE TABLE login (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, pass TEXT)"
    private val borrarTablaLogin = "DROP TABLE IF EXISTS login"

    //Esta es la lista de los usuarios registrados en la base de datos
    private val insertarLogin =
        "INSERT INTO login (usuario, pass) VALUES ('admin', 'admin')," +
                " ('Daniel77', 'root')," +
                " ('JuanCarlos22', 'admin')," +
                " ('DiegoB33', 'diego123')," +
                " ('LucasBM44', 'lucas789')"

    private val crearTablaDatos = "CREATE TABLE datos (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER, edad INTEGER, peso INTEGER, altura INTEGER, sexo TEXT)"

    private val borrarTablaDatos = "DROP TABLE IF EXISTS datos"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(crearTablaLogin)
        db?.execSQL(insertarLogin)
        db?.execSQL(crearTablaDatos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(borrarTablaLogin)
        db?.execSQL(borrarTablaDatos)

        onCreate(db)
    }

}