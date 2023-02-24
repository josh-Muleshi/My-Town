package cd.wayupdotdev.mytown.presentation.screen.profile.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.mytown.data.model.User
import cd.wayupdotdev.mytown.data.repository.UserRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepoImpl,
    private val sharedPreferences: SharedPreferences
): ViewModel(){

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Uninitialized)
    val state: StateFlow<ProfileState>
        get() = _state

    init {
       viewModelScope.launch {
           _state.emit(ProfileState.Loading)
           try {
               userRepo.getCurrentUser().collect { user ->
                   _state.emit(ProfileState.Success(user = user as User))
               }
           } catch (e: Exception) {
               _state.emit(ProfileState.Error(e.localizedMessage ?: e.message.toString()))
           }
       }
    }

    fun logout() = viewModelScope.launch {
        userRepo.signOut()
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("is-auth", false)
        }.apply()
    }
}