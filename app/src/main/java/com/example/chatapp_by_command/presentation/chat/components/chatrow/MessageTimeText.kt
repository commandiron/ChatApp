package com.example.chatapp_by_command.presentation.chat.components.chatrow

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Pending
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageTimeText(
    modifier: Modifier = Modifier,
    messageTime: String,
    messageStatus: MessageStatus
) {
    val messageStat = remember {
        messageStatus
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                modifier = Modifier
                    .padding(top = 1.dp, bottom = 1.dp),
                text = messageTime,
                fontSize = 12.sp
            )
        }

        Icon(
            modifier = Modifier
                .size(16.dp, 12.dp)
                .padding(start = 4.dp),
            imageVector = Icons.Default.DoneAll,//when (messageStatus) {//Pending şu an için olmadığından böyle yapıldı.
            //Pending'i notification'a bağlamak gerekir.
//                MessageStatus.PENDING -> {
//                    Icons.Default.DoneAll
//                }
//                MessageStatus.RECEIVED -> {
//                    Icons.Default.DoneAll
//                }
//                MessageStatus.READ ->{
//                    Icons.Default.DoneAll
//                }
//            },
            tint = if (messageStatus == MessageStatus.READ) Color(0xff0288D1)
            else Color(0xff424242),
            contentDescription = "messageStatus"
        )

    }
}