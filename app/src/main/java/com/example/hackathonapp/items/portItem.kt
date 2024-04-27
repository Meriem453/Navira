package com.example.hackathonapp.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathonapp.R
import com.example.hackathonapp.model.Ship


@Composable
fun portItem(ships: List<Ship>, filteredState: String? = null) {

    val filteredShips: MutableList<Ship> = mutableListOf()

    for (ship in ships) {

        if (ship.type == filteredState) {
            filteredShips.add(ship)
        }

    }


    for (ship in filteredShips) {  // Iterate through filtered or original list

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            val shape = CircleShape

            Image(
                painter = painterResource(id = R.drawable.redrec),
                contentDescription = "icon",
                modifier = Modifier
                    .size(26.dp)
            )

            Text(
                text = ship.name,
                color = Color.Black,
                fontSize = 15.sp
                )

            Text(
                text = ship.type,
                color = Color.Gray,
                fontSize = 15.sp

            )

            Text(
                text = ship.ETA,
                color = Color.Gray,
                fontSize = 15.sp

            )

            Text(
                text = ship.origin,
                color = Color.Gray,
                fontSize = 15.sp

            )

        }

    }

}