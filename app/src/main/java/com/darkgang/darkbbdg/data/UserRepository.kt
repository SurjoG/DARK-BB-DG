
package com.darkgang.darkbbdg.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getAllUsers(): List<UserProfile> {
        return try {
            db.collection("users")
                .get()
                .await()
                .toObjects(UserProfile::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun updateUserRole(
        uid: String,
        role: String
    ): Boolean {
        return try {
            db.collection("users")
                .document(uid)
                .update("role", role)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun setUserApproval(
        uid: String,
        approved: Boolean
    ): Boolean {
        return try {
            db.collection("users")
                .document(uid)
                .update("approved", approved)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
