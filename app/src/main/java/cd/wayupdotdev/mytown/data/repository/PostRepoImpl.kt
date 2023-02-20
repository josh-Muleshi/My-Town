package cd.wayupdotdev.mytown.data.repository

import android.net.Uri
import cd.wayupdotdev.mytown.data.model.Post
import cd.wayupdotdev.mytown.data.utils.FireBaseConstants
import cd.wayupdotdev.mytown.domain.repository.PostRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject


class PostRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
): PostRepo {
    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    override fun getAll() = callbackFlow {
        firestore.collection("${FireBaseConstants.users}/${FireBaseConstants.publication}/${FireBaseConstants.posts}")
            .orderBy(Post::createdAt.name, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null && value == null) {
                    close(error)
                }

                value?.toObjects(Post::class.java).let { posts ->
                    if (!isClosedForSend) {
                        trySend(posts)
                    }
                }
            }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)

    override fun getPostByUid(uidPost: String) = callbackFlow {
        firestore.document("${FireBaseConstants.users}/${FireBaseConstants.publication}/${FireBaseConstants.posts}/$uidPost")
            .addSnapshotListener { value, error ->
                if (error != null && value == null) {
                    close(error)
                }

                value?.toObject<Post>().let {
                    if (!isClosedForSend) {
                        trySend(it)
                    }
                }
            }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)

    override suspend fun add(title: String, description: String, date: String, uri: Uri) {
        val fileRef = storage.reference.child("images/${title.lowercase(Locale.ROOT)}")
        fileRef.putFile(uri).await()
        val imageUrl = fileRef.downloadUrl.await().toString()

        addPostStore(title, description, date)
    }

    private suspend fun addPostStore(title: String, description: String, imageUrl: String){
        val post = Post(
            uid = title.lowercase(Locale.ROOT),
            userUid = currentUser?.uid.toString(),
            description = description,
            imageUrl = imageUrl,
            createdAt = Date(System.currentTimeMillis())
        )
        val doc = firestore.document("${FireBaseConstants.users}/${FireBaseConstants.publication}/${FireBaseConstants.posts}/${post.uid}")
        doc.set(post).await()
    }

    override suspend fun delete(contactUid: String) {
        firestore.document("${FireBaseConstants.users}/${FireBaseConstants.publication}/${FireBaseConstants.posts}/${contactUid}").delete().await()
    }
}