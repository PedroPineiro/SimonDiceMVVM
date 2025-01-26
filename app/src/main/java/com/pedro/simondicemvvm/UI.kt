package com.pedro.simondicemvvm

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
 * UI principal del juego "Simón Dice".
 */
@Composable
fun SimonDiceUI(viewModel: ViewModel) {

    // Observa los datos en vivo desde el ViewModel
    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())

    // Lista para almacenar los colores seleccionados
    val listaColores = remember { mutableStateListOf<Int>() }

    // Observa los colores desde el ViewModel
    val colorRojo by viewModel.colorRojoLiveData.observeAsState(viewModel.getColorRed())
    val colorVerde by viewModel.colorVerdeLiveData.observeAsState(viewModel.getColorGreen())
    val colorAzul by viewModel.colorAzulLiveData.observeAsState(viewModel.getColorBlue())
    val colorAmarillo by viewModel.colorAmarilloLiveData.observeAsState(viewModel.getColorYellow())

    // Caja principal que contiene toda la interfaz
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage, // Carga la imagen desde los recursos
            contentDescription = null, // No se necesita descripción de contenido
            contentScale = ContentScale.Crop, // Escala la imagen para que cubra el fondo
            modifier = Modifier.fillMaxSize()
        )

        // Sección superior: muestra el récord y los aciertos
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(top = 45.dp) // Alineación y margen superior
        ) {
            Row {
                CrearRecordText(record) // Muestra el texto del récord
                Spacer(modifier = Modifier.width(60.dp)) // Espaciado entre el récord y los aciertos
                CrearAciertosText(aciertos) // Muestra el texto de aciertos
            }
        }

        // Sección central: botones de colores y botón de inicio
        Column(

            verticalArrangement = Arrangement.Center, // Centra verticalmente los elementos
            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente los elementos
            modifier = Modifier.fillMaxSize()

        ) {

            Spacer(modifier = Modifier.height(60.dp)) // Espaciado superior

            // Fila de botones: rojo y verde
            Row {

                CrearColorButton(
                    viewModel,
                    listaColores,
                    viewModel.getRandom(),
                    Colores.ROJO.valorColor, // Color rojo
                    colorRojo // Color dinámico observado
                )

                Spacer(modifier = Modifier.width(20.dp)) // Espaciado entre los botones

                CrearColorButton(
                    viewModel,
                    listaColores,
                    viewModel.getRandom(),
                    Colores.VERDE.valorColor, // Color verde
                    colorVerde // Color dinámico observado
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espaciado entre filas

            // Fila de botones: azul y amarillo
            Row {
                CrearColorButton(
                    viewModel,
                    listaColores,
                    viewModel.getRandom(),
                    Colores.AZUL.valorColor, // Color azul
                    colorAzul // Color dinámico observado
                )

                Spacer(modifier = Modifier.width(20.dp)) // Espaciado entre los botones

                CrearColorButton(
                    viewModel,
                    listaColores,
                    viewModel.getRandom(),
                    Colores.AMARILLO.valorColor, // Color amarillo
                    colorAmarillo // Color dinámico observado
                )

            }

            CrearStartButton(viewModel) // Botón para iniciar el juego

        }
    }
}

/**
 * Muestra el número de aciertos del usuario.
 */
@Composable
fun CrearAciertosText(aciertos:Int){
    Text(
        text = "ACIERTOS: $aciertos",
        fontSize = 25.sp, // Tamaño del texto
        fontWeight = FontWeight.Bold, // Peso de fuente en negrita
        color = DarkWhite, // Color del texto

    )
}

/**
 * Muestra el récord máximo del usuario.
 */
@Composable
fun CrearRecordText(record:Int){
    Text(
        text = "RECORD: $record" ,
        fontSize = 25.sp, // Tamaño del texto
        fontWeight = FontWeight.Bold, // Peso de fuente en negrita
        color = DarkWhite // Color del texto
    )
}

/**
 * Botón de color dinámico (rojo, verde, azul o amarillo).
 */
@Composable
fun CrearColorButton(viewModel: ViewModel, listaColores: MutableList<Int>, lista_Random:MutableList<Int>, colorValor:Int, color: Color){

    // Estado para habilitar o deshabilitar el botón
    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.botonesColoresActivos) }

    // Observa cambios en el estado de los botones
    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.botonesColoresActivos
    }

    Button(
        enabled = _activo, // Habilita o deshabilita el botón según el estado
        onClick = {
            viewModel.addColor(colorValor,listaColores, lista_Random) // Agrega el color seleccionado
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color, // Color del botón
        ),
        shape = MaterialTheme.shapes.large, // Forma del botón
        modifier = Modifier.size(170.dp).border(3.dp, DarkWhite, MaterialTheme.shapes.large), // Tamaño y borde
    ){

    }
}

/**
 * Botón de inicio del juego.
 */
@Composable
fun CrearStartButton(viewModel: ViewModel){

    // Estado para habilitar o deshabilitar el botón
    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.startActivo) }

    // Observa cambios en el estado del botón
    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.startActivo
    }
    Button(
        enabled = _activo, // Habilita o deshabilita el botón según el estado
        onClick = { viewModel.setRandom() }, // Acción al hacer clic
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkWhite, // Color del botón
            contentColor = LightDark), // Color del texto
        shape = MaterialTheme.shapes.large, // Forma del botón
        modifier = Modifier
            .padding(top = 50.dp) // Espaciado superior
            .width(250.dp) // Ancho del botón
            .height(100.dp) // Altura del botón
    ) {
        Text(text = "START",
            fontSize = 40.sp, // Tamaño del texto
            fontWeight = FontWeight.Bold, // Peso de fuente en negrita
        )
    }
}
