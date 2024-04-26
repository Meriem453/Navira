package com.example.hackathonapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathonapp.R
import com.example.hackathonapp.items.CustomSearchItem

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
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

        CustomSearchItem(modifier = Modifier.padding(15.dp), search = "Search..", onValueChange = {})

        var state by remember { mutableStateOf(0) }
        val titles = listOf("My Ship", "Weekly Forecast")

        Spacer(modifier = Modifier.height(10.dp))

        TabRow(
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

        } else {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}

