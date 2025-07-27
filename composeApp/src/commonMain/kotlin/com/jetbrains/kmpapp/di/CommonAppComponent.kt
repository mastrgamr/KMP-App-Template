package com.jetbrains.kmpapp.di

import androidx.lifecycle.ViewModelProvider
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import me.tatarka.inject.annotations.Component
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ForScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Component
@MergeComponent(AppScope::class)
@SingleIn(AppScope::class)
abstract class CommonAppComponent : CommonAppComponentMerged {

    @ForScope(AppScope::class)
    abstract val vmFactory: ViewModelProvider.Factory
}

val CommonAppComponent.appViewModelFactoryOwner
    get() = object : ViewModelFactoryOwner {
        override val viewModelFactory: ViewModelProvider.Factory
            get() = vmFactory
    }
