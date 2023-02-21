package cd.wayupdotdev.mytown.presentation.screen.post.business

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.mytown.data.repository.PostRepoImpl
import cd.wayupdotdev.mytown.domain.repository.CustomCameraRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val repo: CustomCameraRepo,
    private val postRepo: PostRepoImpl
): ViewModel() {

    private val _data = MutableStateFlow<PostState>(PostState.Uninitialized)
    val data: StateFlow<PostState>
        get() = _data

    private val _addPostState = MutableStateFlow<PostState>(PostState.Uninitialized)
    val addPostState: StateFlow<PostState>
        get() = _addPostState

    fun addPost(description: String, uri: Uri) = viewModelScope.launch {
        _addPostState.emit(PostState.Loading)
        try {
            postRepo.add(description, uri)
            _addPostState.emit(PostState.Success)
        } catch (t: Throwable) {
            _addPostState.emit(PostState.Error(t.message.toString()))
        }
    }

//    init {
//        getImageUri()
//    }
//    private fun getImageUri() = viewModelScope.launch {
//        _data.emit(PostState.Loading)
//        try {
//            _data.emit(PostState.Success(repo.getImageUri()))
//            Log.e("url", repo.getImageUri().toString())
//        } catch (t: Throwable) {
//            _data.emit(PostState.Error(t.message.toString()))
//        }
//
//    }

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            repo.showCameraPreview(
                previewView,
                lifecycleOwner
            )
        }
    }

    fun captureAndSave(context: Context) {
        viewModelScope.launch {
            repo.captureAndSaveImage(context) {
               launch {
                   _data.emit(PostState.Success)
               }
            }
        }
    }
}