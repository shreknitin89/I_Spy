package com.example.ispy.ui.hints

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ispy.domain.entity.HintEntity
import com.example.ispy.domain.repo.HintRepo
import com.example.ispy.domain.usecase.CameraResult
import com.example.ispy.domain.usecase.LaunchCameraUseCase
import com.example.ispy.ui.HintRouter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class HintsListViewModel(
    repo: HintRepo,
    private val launchCamera: LaunchCameraUseCase,
    private val hintsRouter: HintRouter,
) : ViewModel() {
    private val _hintsLiveData = MutableLiveData<List<HintEntity>>()
    val hintLiveData get() = _hintsLiveData
    private val _cameraLiveData = MutableLiveData<CameraResult>()
    val cameraLiveData get() = _cameraLiveData

    init {
        val data = repo.getHints()
        _hintsLiveData.value = data
    }

    fun launchCameraFlow() {
        launchCamera()
            .onEach { result ->
                _cameraLiveData.postValue(result)
            }
            .onCompletion { }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun newHint(uri: Uri) {
        hintsRouter.route(uri)
    }
}
