
package com.darkgang.darkbbdg.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkgang.darkbbdg.data.UserProfile

@Composable
fun ProfileScreen(
    profile: UserProfile,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF080808)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
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
                    text = "MY PROFILE",
                    color = Color.White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(45.dp))

            Box(
                modifier = Modifier
                    .size(105.dp)
                    .background(
                        Color(0xFFFF1744),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = profile.name
                        .firstOrNull()
                        ?.uppercase() ?: "D",
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = profile.name.ifBlank { "DARK GANG MEMBER" },
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = profile.role,
                color = Color(0xFFFF1744),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(35.dp))

            ProfileInfo("FULL NAME", profile.name)
            ProfileInfo("EMAIL ADDRESS", profile.email)
            ProfileInfo("USER ID", profile.uid)
            ProfileInfo("ACCOUNT ROLE", profile.role)

            ProfileInfo(
                "MEMBERSHIP STATUS",
                if (profile.approved) "APPROVED" else "PENDING APPROVAL"
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF1744)
                )
            ) {
                Text(
                    text = "LOG OUT",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ProfileInfo(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .background(
                Color(0xFF1B1B1B),
                RoundedCornerShape(10.dp)
            )
            .padding(15.dp)
    ) {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = value.ifBlank { "Not available" },
            color = Color.White,
            fontSize = 15.sp
        )
    }
}
