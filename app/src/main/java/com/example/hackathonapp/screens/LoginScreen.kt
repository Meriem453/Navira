package com.example.hackathonapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hackathonapp.R
import com.example.hackathonapp.ViewModels.LoginVM
import com.example.hackathonapp.items.MyTextField
import com.example.hackathonapp.items.SimpleTextField
import com.example.hackathonapp.items.customButton
import com.example.hackathonapp.navigations.Home


@Composable
fun LoginScreen(navController: NavController) {

    val scrollState = rememberScrollState()

val vm = hiltViewModel<LoginVM>()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.loginbg),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()

        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)


            ) {
            Spacer(
                modifier = Modifier.fillMaxHeight (.3f),
            )

            Text(
                text = "Log into ",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ,
                textAlign = TextAlign.Start
            )
            Text(
                text = "Your account",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ,
                textAlign = TextAlign.Start
            )

            Spacer(
                modifier = Modifier.fillMaxHeight (.1f),
            )

            var shipName by remember { mutableStateOf("") }
            var shipPassword by remember { mutableStateOf("") }

            SimpleTextField(
                labelValue = "Enter Ship Name",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                KeyboardOptions(keyboardType = KeyboardType.Text),
                onTextChanged = {
                    shipName = it

                },
                true
            )


            SimpleTextField(
                labelValue = "Enter Ship id",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                KeyboardOptions(keyboardType = KeyboardType.Number),
                onTextChanged = {
                    shipPassword = it
                },
                true
            )

            customButton(
                text = "Login",
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                onButtonClicked = {
                     navController.navigate(Home.route)
                },
                isEnabled = true


            )

        }


    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(NavController(LocalContext.current))


}

