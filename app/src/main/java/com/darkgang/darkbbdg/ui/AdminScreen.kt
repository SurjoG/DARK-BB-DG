
package com.darkgang.darkbbdg.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkgang.darkbbdg.data.UserProfile

@Composable
fun AdminScreen(
    users: List<UserProfile>,
    onApproveUser: (String) -> Unit,
    onChangeUserRole: (String, String) -> Unit,
    onBack: () -> Unit
) {
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onBack) {
                    Text("BACK", color = Color(0xFFFF1744))
                }

                Text(
                    text = "ADMIN PANEL",
                    color = Color(0xFFFF1744),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "TOTAL MEMBERS: ${users.size}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (users.isEmpty()) {
                Text(
                    text = "No members found.",
                    color = Color.LightGray
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(users, key = { it.uid }) { user ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF202020)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Text(
                                    text = user.name.ifBlank {
                                        "Unknown Member"
                                    },
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = user.email,
                                    color = Color.LightGray,
                                    fontSize = 13.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "ROLE: ${user.role}",
                                    color = Color(0xFFFF1744),
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = if (user.approved) {
                                        "STATUS: APPROVED"
                                    } else {
                                        "STATUS: PENDING"
                                    },
                                    color = if (user.approved) {
                                        Color(0xFF69F0AE)
                                    } else {
                                        Color.Yellow
                                    },
                                    fontSize = 12.sp
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (!user.approved) {
                                    Button(
                                        onClick = {
                                            onApproveUser(user.uid)
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF2E7D32)
                                        )
                                    ) {
                                        Text("APPROVE MEMBER")
                                    }
                                }

                                OutlinedButton(
                                    onClick = {
                                        val newRole =
                                            if (user.role == "ADMIN") {
                                                "MEMBER"
                                            } else {
                                                "ADMIN"
                                            }

                                        onChangeUserRole(
                                            user.uid,
                                            newRole
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        if (user.role == "ADMIN") {
                                            "CHANGE TO MEMBER"
                                        } else {
                                            "MAKE ADMIN"
                                        },
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
