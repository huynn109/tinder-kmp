package com.huynn109.kmp.tinder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.essenty.backhandler.BackHandler
import com.huynn109.kmp.tinder.di.sharedModule
import com.huynn109.kmp.tinder.feature.main.MainScreen
import com.huynn109.kmp.tinder.navigation.RootComponent
import com.huynn109.kmp.tinder.navigation.TinderRootComponent
import com.huynn109.kmp.tinder.utils.LocalWindowSizeClass
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun AppWithKoin(rootComponent: RootComponent) {
    KoinApplication(application = {
        modules(sharedModule)
    }) {
        App(rootComponent = rootComponent)
    }
}

@Composable
@Preview
fun App(
    rootComponent: RootComponent,
) {
    MaterialTheme {
        Box {
            // Modifier to fill the maximum size of the parent.
            val fillMaxsizeModifier = Modifier.fillMaxSize()

            // Displays the children of the root component's child stack with a back animation.
            Children(
                modifier = fillMaxsizeModifier,
                stack = remember { rootComponent.childStack },
                animation = backAnimation(
                    backHandler = (rootComponent as TinderRootComponent).backHandler,
                    onBack = rootComponent::onBack
                )
            ) { child ->
                // Renders the main screen if the current child is of type Main.
                when (val screen = child.instance) {
                    is RootComponent.Child.Main -> MainScreen(
                        modifier = fillMaxsizeModifier,
                        component = screen.component
                    )
                }
            }
        }
    }
}

/**
 * Expects a platform-specific implementation of a back animation for a stack of components.
 *
 * @param C The type of the component.
 * @param T The type of the transition.
 * @param backHandler The handler for back navigation events.
 * @param onBack The callback to be invoked when a back navigation event occurs.
 * @return A platform-specific implementation of `StackAnimation` for the given component and transition types.
 */
internal expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>