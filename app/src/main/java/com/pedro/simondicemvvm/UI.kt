package com.pedro.simondicemvvm

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedro.simondicemvvm.datos.Colores
import com.pedro.simondicemvvm.ui.theme.DarkWhite
import com.pedro.simondicemvvm.ui.theme.LightDark


/**
 * app principal del juego
 */
@Composable
fun SimonDiceUI(viewModel: ViewModel) {

    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())


    var lista_colores = remember { mutableStateListOf<Int>() }

    val colorRojo by viewModel.colorRojoLiveData.observeAsState(viewModel.getColorRed())
    val colorVerde by viewModel.colorVerdeLiveData.observeAsState(viewModel.getColorGreen())
    val colorAzul by viewModel.colorAzulLiveData.observeAsState(viewModel.getColorBlue())
    val colorAmarillo by viewModel.colorAmarilloLiveData.observeAsState(viewModel.getColorYellow())



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )



        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(top = 45.dp)
        ) {
            Row {
                crearRecordText(record)
                Spacer(modifier = Modifier.width(60.dp))
                crearAciertosText(aciertos)
            }
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()

        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Row {

                crearColorButton(
                    viewModel,
                    lista_colores,
                    viewModel.getRandom(),
                    Colores.ROJO.valorColor,
                    colorRojo
                )

                Spacer(modifier = Modifier.width(20.dp))

                crearColorButton(
                    viewModel,
                    lista_colores,
                    viewModel.getRandom(),
                    Colores.VERDE.valorColor,
                    colorVerde
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                crearColorButton(
                    viewModel,
                    lista_colores,
                    viewModel.getRandom(),
                    Colores.AZUL.valorColor,
                    colorAzul
                )

                Spacer(modifier = Modifier.width(20.dp))

                crearColorButton(
                    viewModel,
                    lista_colores,
                    viewModel.getRandom(),
                    Colores.AMARILLO.valorColor,
                    colorAmarillo
                )

            }

            crearStartButton(viewModel)

        }
    }
}

/**
 * Interfaz para mostrar el numero de aciertos del usuario
 */
@Composable
fun crearAciertosText(aciertos:Int){
        Text(
            text = "ACIERTOS: $aciertos",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = DarkWhite,

        )
}

/**
 * Interfaz para mostrar el record maximo del usuario en el juego
 */
@Composable
fun crearRecordText(record:Int){
        Text(
            text = "RECORD: $record" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = DarkWhite
        )
}

/**
 * Interfaz con el boton rojo
 */
@Composable
fun crearColorButton(viewModel: ViewModel, listaColores: MutableList<Int>, lista_Random:MutableList<Int>, colorValor:Int, color: Color){

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
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(170.dp).border(3.dp, DarkWhite, MaterialTheme.shapes.large),
    ){

    }
}

/**
 * Interfaz que muestra el boton de start
 */
@Composable
fun crearStartButton(viewModel: ViewModel){

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.startActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.startActivo
    }
        Button(
            enabled = _activo,
            onClick = { viewModel.setRandom() },
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkWhite,
                contentColor = LightDark),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(top = 50.dp)
                .width(250.dp)
                .height(100.dp)
        ) {
            Text(text = "START",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                )
        }
}
