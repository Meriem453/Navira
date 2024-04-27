package com.example.hackathonapp.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hackathonapp.R
import com.example.hackathonapp.ViewModels.HomeVM
import com.example.hackathonapp.ViewModels.LoginVM
import com.example.hackathonapp.items.CustomSearchItem
import com.example.hackathonapp.items.infoItem
import com.example.hackathonapp.items.portItem
import com.example.hackathonapp.model.Ship
import com.example.hackathonapp.navigations.Emergency
import com.example.hackathonapp.navigations.Home
import com.example.hackathonapp.navigations.Map
import com.example.hackathonapp.navigations.Notifications
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val destinationsList = listOf(Home, Emergency, Map)
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val timePicker = rememberMaterialDialogState()
    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.height(62.dp)) {
                destinationsList.forEachIndexed { index, screen ->
                    if (index == 1) {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .size(55.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xff002C70))
                                        .padding(13.dp),
                                    tint = Color.White
                                )


                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                timePicker.show()

                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xff002C70),
                                unselectedIconColor = Color(0xff002C70)
                            )

                        )
                    } else {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = "icon"
                                )
                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                navController.navigate(destinationsList[selectedIndex].route) {
                                    popUpTo(Home.route)
                                    launchSingleTop = true
                                }
                            })
                    }
                }
            }

        },

    ) {
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
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { navController.navigate(Notifications.route) }

                )
            }

            CustomSearchItem(
                modifier = Modifier.padding(15.dp),
                search = "Search..",
                onValueChange = {})

            var state by remember { mutableStateOf(0) }
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

            MaterialDialog(
                dialogState = timePicker,
                //backgroundColor = Color(0xff4172ED).copy(alpha = 0.4f),
                backgroundColor = Color.White,
                properties = DialogProperties(true, true),
                shape = RoundedCornerShape(8.dp)

            ) {
                var selectedTime by remember {
                    mutableStateOf(LocalTime.now())
                }
                timepicker(
                    initialTime = LocalTime.now(),
                    title = "Set unexpected arrival time",
                    colors = TimePickerDefaults.colors(
                        activeTextColor = Color.White,
                        activeBackgroundColor = Color(0xff002C70),
                        inactiveTextColor = Color.White,
                        headerTextColor = Color.Black,
                        selectorColor = Color.White,
                        selectorTextColor = Color.Gray
                    )
                ) {
                    selectedTime = it
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    

                    Button(
                        onClick = {  timePicker.hide()  },
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(35),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {

                        Text(
                            text = "Cancel",
                            color = Color.Black
                        )

                    }
                    
                    Spacer(modifier = Modifier.padding(8.dp) )

                    Button(
                        onClick = { timePicker.hide() },
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff002C70)
                        ),
                        shape = RoundedCornerShape(35)
                    ) {

                        Text(
                            text = "Save",
                            color = Color.White
                        )

                    }
                }

            }

        }

    }



}

@Composable
fun MyShips() {
    val scrollState = rememberScrollState()


    val sampleShips = listOf(
        Ship(1, "Ship1", "Incoming", 2, 50, "Active", "25/04/2024", "MARSEILLE"),
        Ship(2, "Petroleum Tanker 2", "Docked", 1, 75),
        Ship(3, "Passenger Cruise 3", "Waiting", 3, 100),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 40.dp)
        ,

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
                .padding(10.dp),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = FontFamily(listOf(Font(R.font.abel_regular)))
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column() {
                infoItem(
                    icon = R.drawable.arrow_repeat,
                    infotype = "Ship state",
                    shipinfo = sampleShips[0].state
                )
                infoItem(icon = R.drawable.box, infotype = "Type", shipinfo = sampleShips[0].type)
                infoItem(
                    icon = R.drawable.clock_history,
                    infotype = "ETA",
                    shipinfo = sampleShips[0].ETA
                )
                infoItem(
                    icon = R.drawable.globe_americas,
                    infotype = "Origin",
                    shipinfo = sampleShips[0].origin
                )

            }

        }
    }

}

@Composable
fun PortStatus() {
    val states = listOf("Incoming", "In Quai", "In Rade")

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),

        ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
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

    val vm = hiltViewModel<HomeVM>()
    var shipsFB by remember { mutableStateOf(listOf<Ship>())  }

   /* vm.getALlShips {
        shipsFB = it
        Log.d("firebase",it.toString())
    }
    */
    val sampleShips = listOf(
        Ship(1, "Ship1", "Active", 2, 50, "Incoming", "25/04/2024", "MARSEILLE"),
        Ship(2, "Ship2", "Active", 2, 50, "Incoming", "25/04/2024", "MARSEILLE"),
        Ship(3, "Ship3", "Active", 2, 50, "Incoming", "25/04/2024", "MARSEILLE"),
        Ship(3, "Ship4", "Active", 2, 50, "In Rade", "25/04/2024", "MARSEILLE"),
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
    portItem(sampleShips, state_name)

}


//@Preview
//@Composable
//private fun HomeScreenPrev() {
//    HomeScreen(navController)
//}

