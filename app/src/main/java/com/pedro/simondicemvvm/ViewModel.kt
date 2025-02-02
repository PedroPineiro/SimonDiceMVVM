package com.pedro.simondicemvvm

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro.simondicemvvm.datos.ColoresIluminados
import com.pedro.simondicemvvm.datos.Datos
import com.pedro.simondicemvvm.datos.Datos.aciertos
import com.pedro.simondicemvvm.datos.Datos.numRandom
import com.pedro.simondicemvvm.datos.Datos.record
import com.pedro.simondicemvvm.datos.Estados
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Clase ViewModel para gestionar la lógica del juego y los datos relacionados con la UI.
 */
class ViewModel(): ViewModel() {

    // LiveData para observar el estado del juego
    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    // LiveData para observar el récord del usuario
    private val _recordLiveData = MutableLiveData<Int>()
    val recordLiveData: LiveData<Int> get() = _recordLiveData

    // LiveData para observar el número de aciertos del usuario
    private val _aciertosLiveData = MutableLiveData<Int>()
    val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData

    // LiveData para observar el color de los botones
    private var _colorRojoLiveData = MutableLiveData<Color>()
    val colorRojoLiveData : LiveData<Color> get() = _colorRojoLiveData

    private var _colorVerdeLiveData = MutableLiveData<Color>()
    val colorVerdeLiveData : LiveData<Color> get() = _colorVerdeLiveData

    private var _colorAzulLiveData = MutableLiveData<Color>()
    val colorAzulLiveData : LiveData<Color> get() = _colorAzulLiveData

    private var _colorAmarilloLiveData = MutableLiveData<Color>()
    val colorAmarilloLiveData : LiveData<Color> get() = _colorAmarilloLiveData

    var random = Random

    // Inicializa los colores de los botones
    init {
        _colorRojoLiveData.value = ColoresIluminados.ROJO_PARPADEO.colorNomal
        _colorVerdeLiveData.value = ColoresIluminados.VERDE_PARPADEO.colorNomal
        _colorAzulLiveData.value = ColoresIluminados.AZUL_PARPADEO.colorNomal
        _colorAmarilloLiveData.value = ColoresIluminados.AMARILLO_PARPADEO.colorNomal
    }

    // Inicializa el récord y los aciertos
    init {
        _recordLiveData.value = record
        _aciertosLiveData.value = aciertos
    }

    /**
     * Incrementa el número de aciertos del usuario.
     */
    fun incrementarAciertos() {
        aciertos += 1
        _aciertosLiveData.value = aciertos
    }

    /**
     * Incrementa el récord si el número actual de aciertos es mayor.
     */
    fun incrementarRecord() {
        if (getRecord() < getAciertos()) {
            record = aciertos
            _recordLiveData.value = record
        }
    }

    /**
     * Devuelve el número de aciertos del usuario.
     */
    fun getAciertos():Int{
        return aciertos
    }

    /**
     * Devuelve el récord actual.
     */
    fun getRecord():Int{
        return record
    }

    /**
     * Incrementa tanto el número de aciertos como el récord.
     */
    fun incrementarValues(){
        incrementarAciertos()
        incrementarRecord()
    }

    /**
     * Reinicia el número de aciertos a cero.
     */
    fun reiniciarValores(){
        aciertos = 0
        _aciertosLiveData.value = aciertos
    }

    /**
     * Genera una secuencia aleatoria de números y actualiza el estado del juego.
     */
    fun setRandom(){
        numRandom = random.nextInt(4) + 1
        Datos.listaNumerosRandom.add(numRandom)
        estadoLiveData.value = Estados.ADIVINANDO
        cambiarColores(Datos.listaNumerosRandom)
        Log.d("Random", Datos.listaNumerosRandom.toString())
    }

    /**
     * Añade un color a la secuencia del usuario y verifica si ha ganado o perdido.
     */
    fun addColor(numero:Int, listaColoresR: MutableList<Int>, lista_Random:MutableList<Int>){
        listaColoresR.add(numero)
        Datos.listaColores = listaColoresR
        winOrLose(lista_Random, listaColoresR)
    }

    /**
     * Devuelve la lista de números aleatorios generados por la máquina.
     */
    fun getRandom():MutableList<Int>{
        return Datos.listaNumerosRandom
    }

    /**
     * Limpia la lista de números aleatorios.
     */
    fun clearListaRandoms(){
        Datos.listaNumerosRandom.clear()
    }

    /**
     * Limpia la lista de colores.
     */
    fun clearListaColores(lista:MutableList<Int>){
        lista.clear()
    }

    /**
     * Verifica si el usuario ha ganado o perdido el juego.
     */
    fun winOrLose(lista_Random: MutableList<Int>, listaColores: MutableList<Int>){
        if(listaColores.size <= lista_Random.size){
            auxWinOrLose(lista_Random, listaColores)
        }
    }

    /**
     * Función auxiliar para verificar si la secuencia del usuario coincide con la secuencia de la máquina.
     */
    private fun auxWinOrLose(lista_Random:MutableList<Int>,listaColores:MutableList<Int>){
        if(lista_Random == listaColores){
            onWin(listaColores)
            Log.d("random", "ganaste")
            Log.d("randomRe", getRecord().toString())
            Log.d("randomAc", getAciertos().toString())
        }
        else if (lista_Random.subList(0, listaColores.size) == listaColores){
            Log.d("TAG", "CORRECTO")
        }
        else{
            Log.d("random", "perdiste")
            onLose(listaColores)
        }
    }

    /**
     * Lógica para cuando el usuario gana una ronda.
     */
    fun onWin(listaColores: MutableList<Int>) {
        estadoLiveData.value = Estados.INICIO
        incrementarValues()
        clearListaColores(listaColores)
    }

    /**
     * Lógica para cuando el usuario pierde una ronda.
     */
    fun onLose(listaColores: MutableList<Int>){
        estadoLiveData.value = Estados.INICIO
        reiniciarValores()
        clearListaColores(listaColores)
        clearListaRandoms()
    }

    /**
     * Cambia los colores según la secuencia aleatoria generada.
     */
    private fun cambiarColores(lista_Random: MutableList<Int>){
        viewModelScope.launch {
            for(i in 0 until lista_Random.size){
                if(lista_Random[i] == 1){
                    delay(300)
                    _colorRojoLiveData.value = Color(0xFFFF9999)
                    delay(300)
                    _colorRojoLiveData.value = ColoresIluminados.ROJO_PARPADEO.colorNomal
                    delay(300)
                }
                else if(lista_Random[i] == 2){
                    delay(300)
                    _colorVerdeLiveData.value = Color(0xFFA8FFAA)
                    delay(300)
                    _colorVerdeLiveData.value = ColoresIluminados.VERDE_PARPADEO.colorNomal
                    delay(300)
                }
                else if(lista_Random[i] == 3){
                    delay(300)
                    _colorAzulLiveData.value = Color(0xFF5F85FF)
                    delay(300)
                    _colorAzulLiveData.value = ColoresIluminados.AZUL_PARPADEO.colorNomal
                    delay(300)
                }
                else if (lista_Random[i] == 4){
                    delay(300)
                    _colorAmarilloLiveData.value = Color(0xFFFCFFBE)
                    delay(300)
                    _colorAmarilloLiveData.value = ColoresIluminados.AMARILLO_PARPADEO.colorNomal
                    delay(300)
                }
            }
        }
    }

    /**
     * Devuelve el color rojo normal.
     */
    fun getColorRed():Color{
        return ColoresIluminados.ROJO_PARPADEO.colorNomal
    }

    /**
     * Devuelve el color verde normal.
     */
    fun getColorGreen():Color{
        return ColoresIluminados.VERDE_PARPADEO.colorNomal
    }

    /**
     * Devuelve el color azul normal.
     */
    fun getColorBlue():Color{
        return ColoresIluminados.AZUL_PARPADEO.colorNomal
    }

    /**
     * Devuelve el color amarillo normal.
     */
    fun getColorYellow():Color{
        return ColoresIluminados.AMARILLO_PARPADEO.colorNomal
    }
}