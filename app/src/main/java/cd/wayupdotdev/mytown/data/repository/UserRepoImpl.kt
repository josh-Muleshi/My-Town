package cd.wayupdotdev.mytown.data.repository

import cd.wayupdotdev.mytown.data.model.User
import cd.wayupdotdev.mytown.data.utils.FireBaseConstants
import cd.wayupdotdev.mytown.domain.repository.UserRepo
import cd.wayupdotdev.mytown.domain.repository.dto.CustomFirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

     suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        try {
            val user = firebaseAuth.signInWithCredential(credential).await().user!!
            return CustomFirebaseUser(
                uid = user.uid,
                email = user.email.toString(),
                name = user.displayName.toString(),
                profileUrl = user.photoUrl.toString(),
                isValid = true,
                createdAt = Date(System.currentTimeMillis())
            )
        } catch (t: Throwable) {
            throw t
        }
    }

     fun getCurrentUser() = callbackFlow {
        firestore.document("${FireBaseConstants.users}/${currentUser?.uid.toString()}")
            .addSnapshotListener { value, error ->
            if (error != null && value == null) {
                close(error)
            }

            value?.toObject(User::class.java).let { user ->
                if (!isClosedForSend) {
                    trySend(user)
                }
            }
        }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)
}