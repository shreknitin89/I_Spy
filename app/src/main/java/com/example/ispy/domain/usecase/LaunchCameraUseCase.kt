package com.example.ispy.domain.usecase

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

interface LaunchCameraUseCase {
    operator fun invoke(): Flow<CameraResult>
}

sealed class CameraResult {
    data class Success(val uri: Uri) : CameraResult()
    object Fail : CameraResult()
    object NoPermissions : CameraResult()
}

class LaunchCameraUseCaseImpl(
    private val activity: AppCompatActivity,
    permissionsProvider: CameraPermissionsProvider,
    cameraLauncherUseCase: CameraLauncherUseCase,
    private val uriGenerator: PhotoFileUriGenerator
) : LaunchCameraUseCase {

    private val permissionsChannel = Channel<Boolean>()
    private val permissionsLauncher = permissionsProvider.provide {
        permissionsChannel.trySendBlocking(it)
        permissionsChannel.close()
    }

    private val cameraChannel = Channel<Boolean>()
    private val cameraLauncher = cameraLauncherUseCase.provide {
        cameraChannel.trySendBlocking(it)
        cameraChannel.close()
    }

    private fun requestPermissions(): Flow<Boolean> {
        return when {
            isGivenPreviously() -> {
                flowOf(true)
            }
            isDenied() -> {
                flowOf(false)
            }
            else -> {
                permissionsLauncher.launch(Manifest.permission.CAMERA)
                permissionsChannel.receiveAsFlow()
            }
        }
    }

    private fun isGivenPreviously(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isDenied(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_DENIED
    }

    private fun openCamera(): Flow<CameraResult> {
        val uri = uriGenerator.generateNewPhotoFile()
        cameraLauncher.launch(uri)

        return cameraChannel.receiveAsFlow()
            .map { isSaved ->
                if (isSaved) CameraResult.Success(uri)
                else CameraResult.Fail
            }
    }

    override fun invoke(): Flow<CameraResult> {
        return requestPermissions().flatMapConcat { isPermissionsGranted ->
            if (isPermissionsGranted) {
                openCamera()
            } else {
                flowOf(CameraResult.NoPermissions)
            }
        }
    }

}
