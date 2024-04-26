package com.example.hackathonapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackathonapp.model.Quay

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){


    }

}



@Preview
@Composable
private fun MapScreenPrev() {
    MapScreen()
}



@Composable
fun Quay(quay: Quay, ){
    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Card(
        modifier = Modifier
            .size(250.dp, 60.dp)
            .drawBehind {
                drawRoundRect(color = Color.Red, style = stroke)
            },
    ) {

        Text(text = "Hello")

    }

}

@Preview
@Composable
fun QuayPrev () {

    Quay(Quay(1, true, null))
}