package cd.wayupdotdev.mytown.presentation.screen.auth.business

sealed class AuthState {
    data object Uninitialized : AuthState()
    data object Loading : AuthState()
    data class Error(val errorMessage: String) : AuthState()
    data object Success: AuthState()
    var isLoading: Boolean = false
}