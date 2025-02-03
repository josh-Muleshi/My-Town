package cd.wayupdotdev.mytown.presentation.screen.detail.business

import cd.wayupdotdev.mytown.data.model.Post

sealed class DetailState {
    data object Uninitialized: DetailState()
    data object Loading : DetailState()
    data class Error(val message: String) : DetailState()
    data class Success(val post: Post) : DetailState()
}