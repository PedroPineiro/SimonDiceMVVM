package com.pedro.simondicemvvm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedro.simondicemvvm.ui.theme.DarkBlue
import com.pedro.simondicemvvm.ui.theme.DarkGreen
import com.pedro.simondicemvvm.ui.theme.DarkRed
import com.pedro.simondicemvvm.ui.theme.DarkWhite
import com.pedro.simondicemvvm.ui.theme.DarkYellow
import com.pedro.simondicemvvm.ui.theme.LightDark

@Composable
fun SimonDiceUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightDark)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CrearButton(color = DarkGreen, onClick = { println("Botón rojo") })
            CrearButton(color = DarkRed, onClick = { println("Botón azul") })
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CrearButton(color = DarkYellow, onClick = { println("Botón verde") })
            CrearButton(color = DarkBlue, onClick = { println("Botón amarillo") })
        }

    }
}

@Composable
fun CrearButton(color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(150.dp).border(3.dp, DarkWhite, MaterialTheme.shapes.large),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {}
}



@Preview(showBackground = true)
@Composable
fun SimonDiceUIPreview() {
    SimonDiceUI()
}
