
package com.darkgang.darkbbdg.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CallRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun createCallRoom(room: CallRoom): Boolean {
        return try {
            db.collection("callRooms")
                .document(room.roomName)
                .set(room)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getCallRoom(roomName: String): CallRoom? {
        return try {
            db.collection("callRooms")
                .document(roomName)
                .get()
                .await()
                .toObject(CallRoom::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun endCall(roomName: String): Boolean {
        return try {
            db.collection("callRooms")
                .document(roomName)
                .update("active", false)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
