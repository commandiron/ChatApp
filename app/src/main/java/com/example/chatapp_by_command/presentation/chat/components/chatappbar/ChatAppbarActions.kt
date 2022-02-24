package com.example.chatapp_by_command.presentation.chat.components.chatappbar

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ChatAppbarActions(
    onCamClick: (() -> Unit)? = null,
    onCallClick: (() -> Unit)? = null,
    onMorevertBlockUserClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IndicatingIconButton(
            onClick = { Toast.makeText(context, "Videocall Clicked.\n(Not Available)", Toast.LENGTH_SHORT).show() },
            indication = rememberRipple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Videocam,
                contentDescription = null,
                tint = Color.White
            )
        }

        IndicatingIconButton(
            onClick = { Toast.makeText(context, "Voicecall Clicked.\n(Not Available)", Toast.LENGTH_SHORT).show() },
            indication = rememberRipple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = null,
                tint = Color.White
            )
        }

        var showMenu by remember { mutableStateOf(false) }

        IndicatingIconButton(
            onClick = { showMenu = true},
            indication = rememberRipple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = Color.White
            )
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = {
                    onMorevertBlockUserClick?.invoke()}) {
                    Text(text = "Block User")
                }
            }
        }
    }
}