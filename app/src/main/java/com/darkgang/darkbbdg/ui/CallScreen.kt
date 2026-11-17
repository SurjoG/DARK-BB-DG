package com.darkgang.darkbbdg.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CallScreen(
    onJoinCall: (String, Boolean) -> Unit,
    onEndCall: () -> Unit,
    onShareScreen: () -> Unit,
    onBack: () -> Unit
) {
    var roomName by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF080808)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onBack) {
                    Text("BACK", color = Color(0xFFFF1744))
                }

                Text(
                    text = "VOICE & VIDEO CALL",
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(45.dp))

            Text(
                text = "DARK-BB-D-G",
                color = Color(0xFFFF1744),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "PRIVATE CALL ROOM",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = roomName,
                onValueChange = { roomName = it },
                label = { Text("Enter Room Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    onJoinCall(roomName.trim(), false)
                },
                enabled = roomName.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF1744)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("JOIN VOICE CALL")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    onJoinCall(roomName.trim(), true)
                },
                enabled = roomName.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB71C1C)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("JOIN VIDEO CALL")
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedButton(
                onClick = onShareScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    "SHARE SCREEN",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = onEndCall,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text("END CALL", color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "THE BRAND OF DARK GANG",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}
