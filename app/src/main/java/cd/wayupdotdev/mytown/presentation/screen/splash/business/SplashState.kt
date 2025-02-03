package cd.wayupdotdev.mytown.presentation.screen.splash.business

sealed class SplashState {
    data object Uninitialized : SplashState()
    data object Loading : SplashState()
    data class Error(val errorMessage: String) : SplashState()
    data class Success(val isAuth: Boolean) : SplashState()
}