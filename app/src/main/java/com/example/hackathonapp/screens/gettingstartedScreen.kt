package com.example.hackathonapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hackathonapp.R
import com.example.hackathonapp.items.NewsTextButton
import com.example.hackathonapp.items.OnBoardingButton
import com.example.hackathonapp.items.PagerIndicator
import com.example.hackathonapp.model.OnBoardingPage
import com.example.hackathonapp.model.pages
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.launch


@SuppressLint("RememberReturnType")
@OptIn( ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun gettingstartedScreen(videoUri: Uri) {

    val context = LocalContext.current

    val exoPlayer = remember { context.buildExoPlayer(videoUri!!) }

    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    ProvideWindowInsets {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff2980B9).copy(alpha = .4f))
            .padding(10.dp)

        ) {


            val pagerState = rememberPagerState(initialPage = 0) {
                pages.size
            }
            val buttonsState = remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> listOf("", "Next")
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                    }
                }
            }
            HorizontalPager(state = pagerState) { index ->
                OnBoardingPage(page= pages[index])
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PagerIndicator(
                    modifier = Modifier.width(52.dp),
                    pagesSize = pages.size,
                    selectedPage = pagerState.currentPage
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val scope = rememberCoroutineScope()
                    //Hide the button when the first element of the list is empty
                    if (buttonsState.value[0].isNotEmpty()) {
                        NewsTextButton(
                            text = buttonsState.value[0],
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage - 1
                                    )
                                }

                            }
                        )
                    }
                    OnBoardingButton(
                        text = buttonsState.value[1],
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage == 3){
                                    //Navigate to the main screen and save a value in datastore preferences
                                }else{
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage + 1
                                    )
                                }
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }





}


private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }









