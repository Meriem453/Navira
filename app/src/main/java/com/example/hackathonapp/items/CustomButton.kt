package com.example.hackathonapp.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackathonapp.R

@Composable
private fun button(icon: Int) {

    val cornerRadius = 10.dp
    var selected by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { selected = !selected },
        shape =
        RoundedCornerShape(
            topStart = cornerRadius,
            topEnd = cornerRadius,
            bottomStart = cornerRadius,
            bottomEnd = cornerRadius
        ),
        border = BorderStroke(
            1.dp,
            Color.LightGray
        ),
        colors =
        if (selected == false) {
            ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.LightGray
            )
        },
        elevation = ButtonDefaults.buttonElevation(3.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
        )
    }

}

@Preview
@Composable
private fun buttonprev() {
    button(icon = R.drawable.bellicon )
    
}