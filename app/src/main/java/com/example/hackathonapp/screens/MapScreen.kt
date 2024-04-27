package com.example.hackathonapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hackathonapp.R
import com.example.hackathonapp.ViewModels.MapVM
import com.example.hackathonapp.items.InteractivePortMap
import com.example.hackathonapp.model.Quay
import com.example.hackathonapp.model.Ship

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Spots Map",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Box(modifier = Modifier){
                    val vm = hiltViewModel<MapVM>()
                    var list by remember {
                        mutableStateOf(sampleShips)
                    }
                    var quay by remember {
                        mutableStateOf(sampleQuays)
                    }

                    Image(painter = painterResource(id = R.drawable.map_horizontal_devider), contentDescription = null,Modifier.fillMaxSize())
                    InteractivePortMap(ships = list, quays = quay)
                    vm.getALlShips {
                        list=it
                    }
                    vm.getALlQuay {
                        quay = it
                    }

                }

            }

        }

    }

}


//@Preview
//@Composable
//private fun MapScreenPrev() {
//    MapScreen(navController)
//}


// Sample data (replace with your actual data source)
val sampleShips = listOf(
    Ship(1, "Cargo Ship 1", "Active", 2, 50),
    Ship(2, "Petroleum Tanker 2", "Docked", 1, 75),
    Ship(3, "Passenger Cruise 3", "Waiting", 3, 100)
)

val sampleQuays = listOf(
    Quay("Q1", true, null),
    Quay("Q2", true, "2"), // Quay occupied by ship with ID 2
    Quay("Q3", true, null),
    Quay("Q4", true, null),
    Quay("Q3", true, null),
    Quay("Q4", true, null),
    Quay("Q3", true, null),
    Quay("Q4", true, null),
    Quay("Q3", true, null),
    Quay("Q4", true, null),
    Quay("Q3", true, null),
    Quay("Q4", true, null),
    Quay("Q3", true, null),
    Quay("Q4", true, null),

    )

@Preview
@Composable
private fun MapScreenprev() {

}

