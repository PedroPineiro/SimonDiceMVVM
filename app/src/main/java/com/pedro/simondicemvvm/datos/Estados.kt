package com.pedro.simondicemvvm.datos

/**
 * Clase enum para manejar los estados de la aplicación
 */
enum class Estados(val startActivo: Boolean, val botonesColoresActivos:Boolean){

    /**
     * Estados de la aplicación
     * 1. Inicio -> cuando se inicia la aplicación y aun no le dimos al start
     * 2. Generando -> cuando la maquina genera los numeros randoms cuando le das al start y salen las toast informadoras
     * 3. Advininando -> cuando el usuario teclea os botones de colores para adivinar los numeros
     */
    INICIO(startActivo = true, botonesColoresActivos = false),
    ADIVINANDO(startActivo = false, botonesColoresActivos = true),

}