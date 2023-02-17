package cd.wayupdotdev.mytown.presentation.Screen.post.business

import android.net.Uri

sealed class PostState {
    object Uninitialized : PostState()
    object Loading : PostState()
    data class Error(val errorMessage: String) : PostState()
    data class Success(val uri: Uri): PostState()
}