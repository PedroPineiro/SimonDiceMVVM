package com.pedro.simondicemvvm.datos

/**
 * Clase Enum para gestionar los estados de la aplicación.
 *
 * @property startActivo Indica si el botón de inicio está activo.
 * @property botonesColoresActivos Indica si los botones de colores están activos.
 */
enum class Estados(val startActivo: Boolean, val botonesColoresActivos: Boolean) {

    /**
     * Estados de la aplicación:
     * 1. INICIO -> Cuando la aplicación inicia y el botón de inicio aún no ha sido presionado.
     * 2. ADIVINANDO -> Cuando el usuario está presionando los botones de colores para adivinar los números.
     */
    INICIO(startActivo = true, botonesColoresActivos = true),

    ADIVINANDO(startActivo = false, botonesColoresActivos = true),
}