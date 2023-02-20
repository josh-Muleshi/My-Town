package cd.wayupdotdev.mytown.domain.repository

import android.net.Uri
import cd.wayupdotdev.mytown.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepo {
    fun getAll(): Flow<List<Post>?>
    fun getPostByUid(uidPost: String): Flow<Post?>
    suspend fun add(title: String, description: String, date: String, uri: Uri)
    suspend fun delete(contactUid: String)
}