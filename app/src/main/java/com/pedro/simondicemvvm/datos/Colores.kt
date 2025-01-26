package com.pedro.simondicemvvm.datos

import androidx.compose.ui.graphics.Color
import com.pedro.simondicemvvm.ui.theme.DarkBlue
import com.pedro.simondicemvvm.ui.theme.DarkGreen
import com.pedro.simondicemvvm.ui.theme.DarkRed
import com.pedro.simondicemvvm.ui.theme.DarkYellow

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
    ROJO_PARPADEO(colorNomal = DarkRed),
    VERDE_PARPADEO(colorNomal = DarkGreen),
    AZUL_PARPADEO(colorNomal = DarkBlue),
    AMARILLO_PARPADEO(colorNomal = DarkYellow)
}