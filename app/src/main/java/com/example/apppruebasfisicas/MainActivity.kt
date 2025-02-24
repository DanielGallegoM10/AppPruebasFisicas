package com.example.apppruebasfisicas

import NavegacionPrincipal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apppruebasfisicas.BDD.BDDPruebasFisicas
import com.example.apppruebasfisicas.ui.theme.AppPruebasFisicasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPruebasFisicasTheme {
                val bdd = BDDPruebasFisicas(this)
                val db = bdd.writableDatabase
//                bdd.onUpgrade(db, 0, 0)
                NavegacionPrincipal()
            }
        }
    }
}