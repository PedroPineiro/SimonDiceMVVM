package com.pedro.simondicemvvm.datos

object Datos {
    var aciertos = 0 // Número de aciertos del usuario
    var numRandom = 0 // Número aleatorio generado por la máquina
    var record = 0 // Récord máximo del jugador
    val listaNumerosRandom : MutableList<Int> = mutableListOf() // Secuencia generada
    var listaColores : MutableList<Int> = mutableListOf() // Respuestas del jugador
}