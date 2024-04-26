package com.example.hackathonapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackathonapp.Domaine.NotificationSender
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
                    Greeting("Android",this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, activity:Activity) {
    val c= LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = Modifier.
        clickable {
            val notifSender= NotificationSender("e2ppUR7hQOyLrke5i-ha_w:APA91bH-kIx_3owD_FuyfMsfQZQe0DqnFHRNFA8FzWH3W_ibp7xiIaSlZNvSQVRLzw-cpRzKQ4PFWWgtCXov78DjpLzAPQ7if_wCTUqQgnZ3KUPnXjCi_oeJOszk83SlPWiHq93FkFh6","From app",
                "From app",c,activity)
            notifSender.SendNotifications()
        }
    )
val vm= hiltViewModel<LoginVM>()
//    vm.login("meriem","123456"){
//        Toast.makeText(c,it.message,Toast.LENGTH_LONG).show()
//    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonAppTheme {

    }
}