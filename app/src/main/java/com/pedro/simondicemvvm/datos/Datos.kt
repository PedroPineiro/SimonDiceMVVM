package com.pedro.simondicemvvm.datos

import androidx.compose.runtime.mutableStateOf

/**
 * Singleton con los datos de la aplicacion
 */
object Datos {

    //los aciertos que lleva el usuario
    var aciertos = 0
    //las rondas que lleva el usuario
    var rondas = 0
    // los numeros randoms para meter en la lista de la maquina
    var numRandom = 0
    //el record maximo del usuario
    var record = 0
    //la lista de numeros randoms de la maquina
    val listaNumerosRandom : MutableList<Int> = mutableListOf()


    var listaColores : MutableList<Int> = mutableListOf()

}