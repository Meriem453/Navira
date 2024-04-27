package com.example.hackathonapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hackathonapp.R
import com.example.hackathonapp.items.CustomSearchItem
import com.example.hackathonapp.items.infoItem
import com.example.hackathonapp.items.portItem
import com.example.hackathonapp.model.Ship

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
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

        CustomSearchItem(
            modifier = Modifier.padding(15.dp),
            search = "Search..",
            onValueChange = {})

        var state by remember { mutableStateOf(1) }
        val titles = listOf("My Ship", "Port Status")


        TabRow(
            modifier = Modifier.padding(10.dp),
            selectedTabIndex = state,
            containerColor = Color.Transparent,
            indicator =
            { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[state]),
                    color = Color(0xff2980B9)

                )

            },
            tabs = {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        selectedContentColor = Color(0xff2980B9),
                        unselectedContentColor = Color.Black.copy(alpha = 0.6f),
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                fontSize = 17.sp,
                                overflow = TextOverflow.Ellipsis
                            )

                        },
                    )
                }

            }

        )

        Spacer(modifier = Modifier.height(20.dp))

        if (state == 0) {
            MyShips()
        } else {
            PortStatus()
        }
    }
}

@Composable
fun MyShips() {

    val sampleShips = listOf(
        Ship(1, "Cargo Ship 1", "Active", 2, 50),
        Ship(2, "Petroleum Tanker 2", "Docked", 1, 75),
        Ship(3, "Passenger Cruise 3", "Waiting", 3, 100),

    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ships),
            contentDescription = "background",
            modifier = Modifier.size(height = 200.dp, width = 400.dp)
        )

        Text(
            text = "ATLANTIC MONACO",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = FontFamily(listOf(Font(R.font.abel_regular)))
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column() {
                infoItem(icon =  R.drawable.arrow_repeat, infotype = "Ship state", shipinfo = sampleShips[0].state)
                infoItem(icon =  R.drawable.box, infotype = "Type", shipinfo = sampleShips[0].state)
                infoItem(icon =  R.drawable.clock_history, infotype = "ETA", shipinfo = sampleShips[0].state)
                infoItem(icon =  R.drawable.globe_americas, infotype = "Origin", shipinfo = sampleShips[0].state)

            }

        }
    }

}

@Composable
fun PortStatus() {
    val states = listOf("Incoming", "In Quai","In Rade")



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
            ,
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            portFilter(states)
            Spacer(modifier = Modifier.height(10.dp))

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun portFilter(states: List<String>) {

    val sampleShips = listOf(
        Ship(1, "Cargo Ship 1", "Active", 2, 50,"Incoming","25/04/2024","MARSEILLE"),
        Ship(2, "Cargo Ship 2", "Active", 2, 50,"Incoming","25/04/2024","MARSEILLE"),
        Ship(3, "Cargo Ship 1", "Active", 2, 50,"Incoming","25/04/2024","MARSEILLE"),
        Ship(3, "Cargo Ship 1", "Active", 2, 50,"In Rade","25/04/2024","MARSEILLE"),

        )

    var expanded by remember { mutableStateOf(false) }
    var state_name by remember { mutableStateOf("Incoming") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        TextField(
            value = state_name ?: "", onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .padding(start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            placeholder = {
                Text(
                    text = "Enter meeting's team",
                    fontSize = 15.sp,
                    color = Color.Gray,

                    )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }, readOnly = true

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.background(Color.White)
        ) {

            states.forEachIndexed() { position, selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    },
                    onClick = {
                        state_name = selectionOption
                        expanded = false

                    }
                )

            }
        }
    }
    portItem(sampleShips,state_name)

}


//@Preview
//@Composable
//private fun HomeScreenPrev() {
//    HomeScreen(navController)
//}

