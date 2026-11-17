
package com.darkgang.darkbbdg.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkgang.darkbbdg.data.ChatMessage

@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    currentUserId: String,
    onSendMessage: (String) -> Unit,
    onBack: () -> Unit
) {
    var messageText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF080808)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onBack) {
                    Text("BACK", color = Color(0xFFFF1744))
                }

                Text(
                    text = "PRIVATE CHAT",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalDivider(color = Color.DarkGray)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(messages) { message ->
                    val isMine = message.senderId == currentUserId

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = if (isMine) {
                            Alignment.End
                        } else {
                            Alignment.Start
                        }
                    ) {
                        Text(
                            text = message.senderName,
                            color = Color.LightGray,
                            fontSize = 11.sp
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Box(
                            modifier = Modifier
                                .widthIn(max = 280.dp)
                                .background(
                                    color = if (isMine) {
                                        Color(0xFFFF1744)
                                    } else {
                                        Color(0xFF292929)
                                    },
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Text(
                                text = message.message,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text("Write a message...")
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val text = messageText.trim()
                        if (text.isNotEmpty()) {
                            onSendMessage(text)
                            messageText = ""
                        }
                    },
                    enabled = messageText.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF1744)
                    )
                ) {
                    Text("SEND")
                }
            }
        }
    }
}
