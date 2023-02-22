package cd.wayupdotdev.mytown.presentation.screen.home.business

import cd.wayupdotdev.mytown.data.model.Post

sealed class HomeState {
    object Uninitialized: HomeState()
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val posts: ArrayList<Post>) : HomeState()
}