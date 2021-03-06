package com.example.chatapp_by_command.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginEmailCustomOutlinedTextField(entry: String, hint: String, icon: ImageVector, onChange:(String) -> Unit = {}){

    val fullWidthModifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)

    var text by remember { mutableStateOf("") }
    text = entry

    OutlinedTextField(
        modifier = fullWidthModifier,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.onBackground,
            backgroundColor =  MaterialTheme.colors.background,
            focusedIndicatorColor = MaterialTheme.colors.onBackground),
        value = text,
        label = { Text(text = hint, modifier = Modifier.alpha(0.3f)) },
        onValueChange = {
            text = it
            onChange(it)},
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    )
}

@Composable
fun LoginPasswordCustomOutlinedTextField(entry: String, hint: String, icon: ImageVector, onChange:(String) -> Unit = {}){

    val fullWidthModifier = Modifier.fillMaxWidth().padding(2.dp)

    var text by remember { mutableStateOf("") }
    text = entry
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = fullWidthModifier,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.onBackground,
            backgroundColor =  MaterialTheme.colors.background,
            focusedIndicatorColor = MaterialTheme.colors.onBackground),
        value = text,
        label = { Text(hint, modifier = Modifier.alpha(0.3f)) },
        onValueChange = {
            text = it
            onChange(it)},
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(imageVector = image, "")
            }
        }
    )
}

