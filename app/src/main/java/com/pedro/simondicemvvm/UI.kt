package com.pedro.simondicemvvm

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedro.simondicemvvm.datos.Colores
import com.pedro.simondicemvvm.datos.ColoresIluminados
import com.pedro.simondicemvvm.datos.Datos
import com.pedro.simondicemvvm.datos.Estados

/**
 * Interfaz para mostrar el numero de aciertos del usuario
 */
@Composable
fun showAciertos(aciertos:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 90.dp, start = 126.dp)) {

        Text(text = "Aciertos: $aciertos" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        Log.d("Record", aciertos.toString())

    }

}

/**
 * Interfaz para mostrar el record maximo del usuario en el juego
 */
@Composable
fun RecordMaximo(record:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(top = 50.dp, start = 130.dp)) {

        Text(text = "Record: $record" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

    }
}

/**
 * Interfaz con el boton rojo
 */
@Composable
fun buttonColor(viewModel: ViewModel, listaColores: MutableList<Int>, lista_Random:MutableList<Int>, colorValor:Int, color: Color){

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.botonesColoresActivos) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.botonesColoresActivos
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.addColor(colorValor,listaColores, lista_Random)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ){

    }
}





/**
 * Interfaz para mostrar las rondas que lleva el usuario
 */
@Composable
fun showRondas(numeroRondas:Int){

    Text(

        text = "Ronda: $numeroRondas",

        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 100.dp)

    )

}

/**
 * Interfaz que muestra el boton de start
 */
@Composable
fun showButtonStart(viewModel: ViewModel){

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.startActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.startActivo
    }


    val rosa = Color(0xFFFF00C9)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp))
    {
        Button(
            enabled = _activo,
            onClick = { viewModel.setRandom() },
            colors = ButtonDefaults.buttonColors(
                containerColor = rosa,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(145.dp)
                .clip(RectangleShape)
        ) {
            Text(text = "Start",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)

        }
    }


}



/**
 * app principal del juego
 */
@Composable
fun myApp(viewModel: ViewModel) {

    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val rondas by viewModel.rondasLiveData.observeAsState(viewModel.getRondas())
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())



    var lista_colores = remember { mutableStateListOf<Int>() }

    val colorRojo by viewModel.colorRojoLiveData.observeAsState(viewModel.getColorRed())
    val colorVerde by viewModel.colorVerdeLiveData.observeAsState(viewModel.getColorGreen())
    val colorAzul by viewModel.colorAzulLiveData.observeAsState(viewModel.getColorBlue())
    val colorAmarillo by viewModel.colorAmarilloLiveData.observeAsState(viewModel.getColorYellow())



    Box (modifier = Modifier
        .fillMaxSize()
    ){
        /*val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

         */

        Column {
            RecordMaximo(record)
            showAciertos(aciertos)
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.ROJO.valorColor,
                    colorRojo
                )
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.VERDE.valorColor, colorVerde)
            }

            Row {
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AZUL.valorColor, colorAzul)
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AMARILLO.valorColor, colorAmarillo)
            }

            showRondas(rondas)

            showButtonStart(viewModel)



        }
    }

}



