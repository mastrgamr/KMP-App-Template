package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@Inject
@ContributesTo(AppScope::class)
interface DataProviders {

    @Provides
    fun providesHttpClient(): HttpClient {
        val json = Json { ignoreUnknownKeys = true }
        return HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    @Provides
    fun providesMuseumApi(client: HttpClient): MuseumApi {
        return KtorMuseumApi(client)
    }

    @Provides
    fun providesMuseumStorage(): MuseumStorage {
        return InMemoryMuseumStorage()
    }

    @Provides
    fun providesMuseumRepository(
        museumApi: MuseumApi,
        museumStorage: MuseumStorage,
    ): MuseumRepository {
        return MuseumRepository(museumApi, museumStorage)
    }
}
