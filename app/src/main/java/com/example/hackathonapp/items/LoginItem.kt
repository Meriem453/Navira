package com.example.hackathonapp.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import com.example.hackathonapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    labelValue: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        label = {
            Text(
                text = labelValue,
                color = Color.White,
                fontWeight=FontWeight.Bold
            )
        },
        modifier = modifier,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            containerColor = Color.Transparent,
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        keyboardOptions = keyboardOptions,
        isError = !errorStatus

    )

}

@Composable
fun customButton(
    text: String,
    modifier: Modifier,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
) {
    val cornerRadius = 10.dp
    var selected by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = {
            onButtonClicked.invoke()

            // navController.navigate(route)

        },
        modifier = modifier,
        shape =
        RoundedCornerShape(
            topStart = cornerRadius,
            topEnd = cornerRadius,
            bottomStart = cornerRadius,
            bottomEnd = cornerRadius
        ),
        border = BorderStroke(
            1.dp,
            Color.Black
        ),
        colors =
        if (selected == false) {
            ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        },
        elevation = ButtonDefaults.buttonElevation(3.dp),
        enabled = isEnabled

    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    label: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    readOnly: Boolean = false,
    height: Dp =  42.dp,
    trailingIcon: ImageVector? = null
) {
    Column(modifier = modifier) {
        Text(text = label)
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = "",
            onValueChange = {},
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            readOnly = readOnly,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(height)
                    .background(Color(0xFFEFEEEE)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f).padding(horizontal = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    it.invoke()
                }
                trailingIcon?.let {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = trailingIcon, contentDescription = null, tint = Color(0xFF828282))
                    }
                }
            }
        }
    }
}