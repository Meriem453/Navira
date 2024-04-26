package com.example.hackathonapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
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
import com.example.hackathonapp.screens.HomeScreen
import com.example.hackathonapp.screens.gettingstartedScreen
import com.example.hackathonapp.ui.theme.HackathonAppTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackathonAppTheme {
                // A surface container using the 'background' color from the theme
             gettingstartedScreen(getVideoUri())
            }
        }
    }

    private fun getVideoUri(): Uri {
        val rawId = resources.getIdentifier("ships", "raw", packageName)
        val videoUri = "android.resource://$packageName/$rawId"
        return Uri.parse(videoUri)
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