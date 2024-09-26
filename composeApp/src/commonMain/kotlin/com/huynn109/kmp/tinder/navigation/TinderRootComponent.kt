package com.huynn109.kmp.tinder.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.huynn109.kmp.tinder.feature.main.buildMainComponent
import kotlinx.serialization.Serializable

/**
 * The root component for the Tinder application.
 *
 * @property navigation The stack navigation for managing the navigation stack.
 */
class TinderRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    /**
     * The child stack representing the current navigation state.
     */
    override val childStack: Value<ChildStack<*, RootComponent.Child>>
        get() = childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createScreen
        )

    /**
     * Handles the push navigation event.
     *
     * @param page The page to navigate to.
     */
    override fun onPush(page: Page) {
        // Implementation for handling push navigation
    }

    /**
     * Handles the back navigation event.
     */
    override fun onBack() {
        navigation.pop()
    }

    /**
     * Creates a screen based on the given configuration.
     *
     * @param config The configuration for the screen.
     * @param componentContext The context for the component.
     * @return The created child component.
     */
    private fun createScreen(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            Config.Main -> RootComponent.Child.Main(
                buildMainComponent(
                    componentContext,
                    onPush = {})
            )
        }
}

/**
 * The configuration for the navigation stack.
 */
@Serializable
sealed interface Config {
    /**
     * The main configuration.
     */
    @Serializable
    data object Main : Config
}