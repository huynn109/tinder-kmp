package com.huynn109.kmp.tinder.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.huynn109.kmp.tinder.feature.main.MainComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onPush(page: Page)
    fun onBack()
    sealed class Child {
        data class Main(val component: MainComponent) : Child()
    }
}