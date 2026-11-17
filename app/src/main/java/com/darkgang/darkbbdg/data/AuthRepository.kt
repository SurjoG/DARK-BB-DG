
package com.darkgang.darkbbdg.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun currentUser() = auth.currentUser

    suspend fun signIn(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val result = auth
                .signInWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user?.uid ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user?.uid ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
