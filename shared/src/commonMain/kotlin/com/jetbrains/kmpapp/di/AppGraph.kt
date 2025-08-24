package com.jetbrains.kmpapp.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(
    AppScope::class,
    isExtendable = true,
)
interface AppGraph
