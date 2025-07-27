package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.ViewModel
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.screens.list.ListViewModel
import com.teobaranga.kotlin.inject.viewmodel.runtime.ContributesViewModel
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.AssistedFactory
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope

@Inject
@ContributesViewModel(DetailScope::class, assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)

    @AssistedFactory
    interface Factory {
        operator fun invoke(museumRepository: MuseumRepository): DetailViewModel
    }
}
