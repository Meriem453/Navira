package com.example.hackathonapp.model


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.9f),
    page: Page,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = page.title,
            fontSize = 36.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = page.description,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold

        )

    }
}

@Preview
@Composable
private fun OnBoardingPageprev() {

    val page = Page(
        title = "Welcome Aboard!",
        description = "Explore the high seas of opportunity with our Cargo Ship Position Management app."
    )
    OnBoardingPage(page = page)
}


