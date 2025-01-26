package com.pedro.simondicemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * Clase MainActivity donde ejecutamos la aplicación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Creamos un objeto de la clase ViewModel y lo pasamos a la composable SimonDiceUI.
        val viewModel = ViewModel()
        enableEdgeToEdge()
        setContent {
            SimonDiceUI(viewModel)
        }
    }
}