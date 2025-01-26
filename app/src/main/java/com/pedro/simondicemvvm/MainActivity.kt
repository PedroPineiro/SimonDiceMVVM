package com.pedro.simondicemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


/**
 * Clase mainActivity donde ejecutamos la aplicación
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //creamos un objeto de la clase viewmodel y los pasamos a la composable myApp
        val viewModel = ViewModel()
        enableEdgeToEdge()
        setContent {
            myApp(viewModel)
        }
    }
}
