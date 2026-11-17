
package com.darkgang.darkbbdg.data

data class UserProfile(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "MEMBER",
    val approved: Boolean = false
)

data class MembershipRequest(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val status: String = "PENDING"
)

data class ChatMessage(
    val id: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val message: String = "",
    val timestamp: Long = 0L
)

data class CallRoom(
    val roomName: String = "",
    val createdBy: String = "",
    val active: Boolean = false
)
