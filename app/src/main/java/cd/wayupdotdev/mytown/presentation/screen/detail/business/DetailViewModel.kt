package cd.wayupdotdev.mytown.presentation.screen.detail.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.mytown.data.model.Post
import cd.wayupdotdev.mytown.data.repository.PostRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    //private val localPostRepository: LocalPostRepository,
    private val postRepository: PostRepoImpl
) : ViewModel() {

    private val _data = MutableStateFlow<DetailState>(DetailState.Uninitialized)
    val data: StateFlow<DetailState>
        get() = _data

    fun getPostByUid(uidPost: String) = viewModelScope.launch {
        _data.emit(DetailState.Loading)
        try {
            postRepository.getPostByUid(uidPost).collect { post ->
                _data.emit(DetailState.Success(post = post as Post))
            }
        } catch (t: Throwable) {
            _data.emit(DetailState.Error(t.message.toString()))
        }
    }

    fun addToFavorite(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            //localPostRepository.addToFavorite(post)
        }
    }
}