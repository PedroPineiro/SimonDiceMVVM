package com.pedro.simondicemvvm

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ViewModel : ViewModel() {
    // Estado básico del juego
    var gameState = mutableStateOf("Presiona 'Empezar' para jugar")
        private set

    // Metodo para iniciar el juego
    fun startGame() {
        gameState.value = "Juego iniciado: sigue la secuencia"
    }
}
