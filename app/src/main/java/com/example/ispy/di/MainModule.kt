package com.example.ispy.di

import com.example.ispy.data.repo.HintRepoImpl
import com.example.ispy.domain.repo.HintRepo
import com.example.ispy.domain.usecase.CameraLauncherUseCase
import com.example.ispy.domain.usecase.CameraLauncherUseCaseImpl
import com.example.ispy.domain.usecase.CameraPermissionsLauncher
import com.example.ispy.domain.usecase.CameraPermissionsProvider
import com.example.ispy.domain.usecase.LaunchCameraUseCase
import com.example.ispy.domain.usecase.LaunchCameraUseCaseImpl
import com.example.ispy.domain.usecase.PhotoFileUriGenerator
import com.example.ispy.domain.usecase.PhotoFileUriGeneratorImpl
import com.example.ispy.ui.MainActivity
import com.example.ispy.ui.hints.HintsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { HintsListViewModel(get()) }
}

val repoModule = module {
    single<HintRepo> {
        HintRepoImpl(androidContext())
    }
}

val useCaseModule = module {
    single<CameraPermissionsProvider> { (activity: MainActivity) ->
        CameraPermissionsLauncher(activity)
    }
    single<CameraLauncherUseCase> { (activity: MainActivity) ->
        CameraLauncherUseCaseImpl(activity)
    }
    single<PhotoFileUriGenerator> { (activity: MainActivity) ->
        PhotoFileUriGeneratorImpl(activity)
    }
    single<LaunchCameraUseCase> { (activity: MainActivity) ->
        LaunchCameraUseCaseImpl(activity, get(), get(), get())
    }
}
