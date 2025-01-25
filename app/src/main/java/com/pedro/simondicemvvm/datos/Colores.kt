package com.pedro.simondicemvvm.datos

import androidx.compose.ui.graphics.Color

/**
 * Clase enum donde tenemos los colores asociados a un valor en concreto
 */
enum class Colores(val valorColor:Int){
    ROJO(1),
    VERDE(2),
    AZUL(3),
    AMARILLO(4)
}

enum class ColoresIluminados(var colorNomal : Color, var colorIluminado:Color = Color.Transparent){
    ROJO_PARPADEO(colorNomal = Color(0xFFFF0000)),
    VERDE_PARPADEO(colorNomal = Color(0xFF00FE08)),
    AZUL_PARPADEO(colorNomal = Color(0xFF0017FF)),
    AMARILLO_PARPADEO(colorNomal = Color(0xFFF0FF00))
}