package com.jetbrains.kmpapp.screens.list

import androidx.lifecycle.ViewModelProvider
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesSubcomponent
import software.amazon.lastmile.kotlin.inject.anvil.ForScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesSubcomponent(ListScope::class)
@SingleIn(ListScope::class)
interface ListComponent {

    val vmFactory: @ForScope(ListScope::class) ViewModelProvider.Factory

    @ContributesSubcomponent.Factory(AppScope::class)
    interface Factory {
        fun createListComponent(userName: String): ListComponent
    }
}

val ListComponent.viewModelFactoryOwner
    get() = object : ViewModelFactoryOwner {
        override val viewModelFactory: ViewModelProvider.Factory
            get() = vmFactory
    }
