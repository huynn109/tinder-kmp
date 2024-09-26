package com.huynn109.kmp.tinder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.huynn109.kmp.tinder.navigation.RootComponent
import com.huynn109.kmp.tinder.navigation.TinderRootComponent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIViewController

typealias MainViewController = (backDispatcher: BackDispatcher) -> UIViewController

fun debugBuild() {
    Napier.base(DebugAntilog())
}

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController(backDispatcher: BackDispatcher) =
    ComposeUIViewController(configure = { onFocusBehavior = OnFocusBehavior.DoNothing }) {
        PredictiveBackGestureOverlay(
            backDispatcher = backDispatcher,
            backIcon = null,
            modifier = Modifier.fillMaxSize()
        ) {
            val rootComponent: RootComponent = remember {
                TinderRootComponent(
                    componentContext = DefaultComponentContext(
                        lifecycle = LifecycleRegistry(),
                        backHandler = backDispatcher
                    ),
                )
            }
            debugBuild()
            AppWithKoin(
                rootComponent = rootComponent
            )
        }
    }