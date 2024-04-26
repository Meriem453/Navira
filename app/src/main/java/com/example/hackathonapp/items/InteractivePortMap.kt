package com.example.hackathonapp.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hackathonapp.R
import com.example.hackathonapp.model.Quay
import com.example.hackathonapp.model.Ship

@Composable
fun InteractivePortMap(ships: List<Ship>, quays: List<Quay>) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically

    )
    {
        items(quays.chunked(2)) { quayPair -> // // Chunk quays into pairs

            Box(modifier = Modifier.fillMaxWidth()) {
                // ... rest of your code (Column, QuayPair, etc.)
            }
            Column(modifier = Modifier.padding(4.dp)) {
                QuayPair(ships = ships, quays = quayPair) // Display two quays vertically
                Spacer(modifier = Modifier.height(8.dp)) // Add vertical spacing between quay pairs
            }
            Image(
                painter = painterResource(id = R.drawable.map_vertical_devider),
                contentDescription = "map devider"
            )

        }
    }
}

@Composable
fun QuayPair(ships: List<Ship>, quays: List<Quay>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        QuayCard(quay = quays[0], ships = ships) // Display first quay
        Divider( // Add horizontal divider between quays
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.Gray
        )

        //Image(painter = painterResource(id = R.drawable.map_horizontal_devider), contentDescription = "horizontal dev")
        QuayCard(quay = quays[1], ships = ships) // Display second quay
    }
}

@Composable
fun QuayCard(quay: Quay = Quay("1", true, "Q2"), ships: List<Ship>, modifier: Modifier = Modifier) {
    val shipName = getShipName(ships, quay.occupiedBy)
    Card(
        modifier = modifier
            .padding(4.dp)
            .width(100.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (quay.available) {
                Quay(quay = quay)
            } else {
                Image( // Display ship image if occupied
                    painter = painterResource(id = R.drawable.ship),
                    modifier = Modifier.size(350.dp),
                    contentDescription = "Ship on Quay"
                )
            }
        }
    }
}


// Function to retrieve ship name based on occupiedBy ID (assuming unique IDs)
fun getShipName(ships: List<Ship>, occupiedBy: String?): String {
    return ships.find { it.name == occupiedBy }?.name ?: ""
}
