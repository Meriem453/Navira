package com.example.hackathonapp.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackathonapp.model.Quay

@Composable
fun Quay(quay: Quay) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    val brush = Brush.linearGradient(
        colors = listOf(Color.White, Color.White),
    )

    Column(
        modifier = Modifier
            .size(50.dp, 100.dp)
            .border(
                width = 1.dp,
                brush = brush,
                shape = RoundedCornerShape(10.dp)
            )
            .background(Color.Cyan)

        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly


    ) {

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "B-02",
            modifier = Modifier.padding(1.dp)
        )

    }

}

@Preview
@Composable
fun QuayPrev() {
    Quay(Quay(1, true, null))
}