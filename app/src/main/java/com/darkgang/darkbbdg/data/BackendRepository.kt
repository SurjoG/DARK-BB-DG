package com.darkgang.darkbbdg.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BackendRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getUserProfile(uid: String): UserProfile? {
        return try {
            db.collection("users")
                .document(uid)
                .get()
                .await()
                .toObject(UserProfile::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveUserProfile(profile: UserProfile): Boolean {
        return try {
            db.collection("users")
                .document(profile.uid)
                .set(profile)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun submitMembershipRequest(
        request: MembershipRequest
    ): Boolean {
        return try {
            db.collection("membershipRequests")
                .document(request.uid)
                .set(request)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

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
}

