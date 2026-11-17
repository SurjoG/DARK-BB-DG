
package com.darkgang.darkbbdg.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class MessageRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun sendMessage(message: ChatMessage): Boolean {
        return try {
            db.collection("messages")
                .add(message)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getMessages(): List<ChatMessage> {
        return try {
            db.collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .get()
                .await()
                .toObjects(ChatMessage::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun deleteMessage(messageId: String): Boolean {
        return try {
            db.collection("messages")
                .document(messageId)
                .delete()
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
