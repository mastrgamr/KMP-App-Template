package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.ViewModelProvider
import com.jetbrains.kmpapp.screens.list.ListScope
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesSubcomponent
import software.amazon.lastmile.kotlin.inject.anvil.ForScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesSubcomponent(DetailScope::class)
@SingleIn(DetailScope::class)
interface DetailComponent {

    val vmFactory: @ForScope(DetailScope::class) ViewModelProvider.Factory

    @ContributesSubcomponent.Factory(AppScope::class)
    interface Factory {
        fun createDetailComponent(userName: String): DetailComponent
    }
}

val DetailComponent.viewModelFactoryOwner
    get() = object : ViewModelFactoryOwner {
        override val viewModelFactory: ViewModelProvider.Factory
            get() = vmFactory
    }
