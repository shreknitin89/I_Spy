package com.example.ispy.ui.hints

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ispy.domain.entity.HintEntity
import com.example.ispy.domain.repo.HintRepo
import com.example.ispy.domain.usecase.CameraResult

class HintsListViewModel(
    repo: HintRepo
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
        _cameraLiveData.postValue(CameraResult.Success(Uri.EMPTY))
//        launchCamera()
//            .onEach { result ->
//                _cameraLiveData.postValue(result)
//            }
//            .onCompletion {  }
//            .catch {  }
//            .launchIn(viewModelScope)
    }

    fun newHint(uri: Uri) {
//        router.route(uri)
    }
}
