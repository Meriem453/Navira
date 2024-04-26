package com.example.hackathonapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackathonapp.ViewModels.LoginVM
import com.example.hackathonapp.ui.theme.HackathonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackathonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val c= LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
val vm= hiltViewModel<LoginVM>()
    vm.login("meriem","123456"){
        Toast.makeText(c,it.message,Toast.LENGTH_LONG).show()
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonAppTheme {
        Greeting("Android")
    }
}