package com.example.hackathonapp.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun infoItem(icon: Int, infotype:String, shipinfo: String,) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        val shape = CircleShape

        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = icon),
            modifier = Modifier
                .border(2.dp, Color.White, shape)
                .background(Color.White, shape)
                .size(36.dp)
        )

        Text(
            text = infotype,
            style = MaterialTheme.typography.titleLarge,

            color = Color.Black,

        )

        Text(
            text = shipinfo,
            color = Color.Gray,
            style = MaterialTheme.typography.titleLarge,
        )

    }
}