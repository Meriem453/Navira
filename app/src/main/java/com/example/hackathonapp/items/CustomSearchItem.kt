package com.example.hackathonapp.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchItem(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Color(0XFF101921).copy(alpha = .4f))

    ) {
        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFF0),
                focusedIndicatorColor = Color.Transparent, cursorColor = Color(0XFF070E14),
                disabledTextColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
    }

}