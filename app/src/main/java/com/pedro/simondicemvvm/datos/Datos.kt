package com.pedro.simondicemvvm.datos

/**
 * Objeto para almacenar los datos del juego.
 */
object Datos {
    /**
     * Número de aciertos del usuario.
     */
    var aciertos = 0

    /**
     * Número aleatorio generado por la máquina.
     */
    var numRandom = 0

    /**
     * Récord máximo del jugador.
     */
    var record = 0

    /**
     * Secuencia de números aleatorios generados.
     */
    val listaNumerosRandom: MutableList<Int> = mutableListOf()

    /**
     * Respuestas del jugador.
     */
    var listaColores: MutableList<Int> = mutableListOf()
}