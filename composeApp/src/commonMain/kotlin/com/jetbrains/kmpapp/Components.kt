package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.di.CommonAppComponent
import com.jetbrains.kmpapp.di.create
import com.jetbrains.kmpapp.screens.detail.DetailComponent
import com.jetbrains.kmpapp.screens.list.ListComponent

val appComponent = CommonAppComponent::class.create()

var listComponent: ListComponent? = appComponent.createListComponent("userName")
    private set

var detailComponent: DetailComponent? = appComponent.createDetailComponent("userName")
    private set

