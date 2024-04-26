package com.example.hackathonapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathonapp.R

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.mapscreenbg),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()

        )

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(50.dp)

                )
            }
            Column(
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 20.dp
                ),

                ) {
                Text(
                    text = "Navire",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Spots Map",
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
        }

    }

}


@Preview
@Composable
private fun MapScreenPrev() {
    MapScreen()
}
