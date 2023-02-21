package cd.wayupdotdev.mytown.presentation.screen.post.business

import android.net.Uri

sealed class PostState {
    object Uninitialized : PostState()
    object Loading : PostState()
    data class Error(val errorMessage: String) : PostState()
    //data class Success(val uri: Uri): PostState()
    object Success : PostState()
}