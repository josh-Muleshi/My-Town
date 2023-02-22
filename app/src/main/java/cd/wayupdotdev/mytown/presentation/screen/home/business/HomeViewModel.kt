package cd.wayupdotdev.mytown.presentation.screen.home.business

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
class HomeViewModel @Inject constructor(
    //private val localPostRepository: LocalPostRepository,
    private val postRepository: PostRepoImpl
) : ViewModel() {

    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    init {
        getAllPosts()
    }

    private fun getAllPosts() = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            postRepository.getAll().collect { posts ->
                _data.emit(HomeState.Success(posts = posts as ArrayList<Post>))
            }
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }

    fun addToFavorite(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            //localPostRepository.addToFavorite(post)
        }
    }
}