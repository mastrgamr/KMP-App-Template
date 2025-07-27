package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class MuseumRepository(
    @Assisted private val museumApi: MuseumApi,
    @Assisted private val museumStorage: MuseumStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        museumStorage.saveObjects(museumApi.getData())
    }

    fun getObjects(): Flow<List<MuseumObject>> = museumStorage.getObjects()

    fun getObjectById(objectId: Int): Flow<MuseumObject?> = museumStorage.getObjectById(objectId)
}
