package cd.wayupdotdev.mytown.presentation.screen.profile.business

import cd.wayupdotdev.mytown.data.model.User

sealed class ProfileState {
    data object Uninitialized : ProfileState()
    data object Loading : ProfileState()
    data class Error(val errorMessage: String) : ProfileState()
    data class Success(val user: User): ProfileState()
}