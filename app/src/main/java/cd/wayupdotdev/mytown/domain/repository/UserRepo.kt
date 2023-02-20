package cd.wayupdotdev.mytown.domain.repository

import cd.wayupdotdev.mytown.data.model.User
import cd.wayupdotdev.mytown.domain.repository.dto.CustomFirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser
    fun getCurrentUser(): Flow<User?>
}