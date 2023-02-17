package cd.wayupdotdev.mytown.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CustomCameraRepo {

    suspend fun captureAndSaveImage(context:Context)
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )
    fun getImageUri(): Uri
}