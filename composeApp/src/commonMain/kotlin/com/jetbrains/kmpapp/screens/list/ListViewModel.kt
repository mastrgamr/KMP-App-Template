package com.jetbrains.kmpapp.screens.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.teobaranga.kotlin.inject.viewmodel.runtime.ContributesViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.AssistedFactory
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope

@Inject
@ContributesViewModel(ListScope::class, assistedFactory = ListViewModel.Factory::class)
class ListViewModel(
    museumRepository: MuseumRepository
) : ViewModel() {

    init {
        museumRepository.initialize()
    }

    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @AssistedFactory
    interface Factory {
        operator fun invoke(museumRepository: MuseumRepository): ListViewModel
    }
}
