package com.huynn109.kmp.tinder.feature.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.huynn109.kmp.tinder.feature.feed.buildFeedComponent
import com.huynn109.kmp.tinder.feature.liked.buildLikedComponent
import com.huynn109.kmp.tinder.feature.profile.buildProfileComponent
import com.huynn109.kmp.tinder.navigation.Page
import kotlinx.serialization.Serializable

fun buildMainComponent(
    componentContext: ComponentContext,
    onPush: (Page) -> Unit
): MainComponent = MainComponentImpl(
    componentContext = componentContext,
    onPush = onPush
)

class MainComponentImpl(
    val componentContext: ComponentContext,
    val onPush: (Page) -> Unit,
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack: Value<ChildStack<Config, MainComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Feed,
        key = "Main",
        childFactory = ::child
    )

    override val childStack: Value<ChildStack<*, MainComponent.Child>> = stack

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): MainComponent.Child = when (config) {
        Config.Feed -> MainComponent.Child.Feed(
            buildFeedComponent(
                componentContext = componentContext,
                onPush = onPush,
            )
        )

        Config.Liked -> MainComponent.Child.Liked(
            buildLikedComponent(
                componentContext = componentContext,
                onPush = onPush
            )
        )

        Config.Profile -> MainComponent.Child.Profile(
            buildProfileComponent(
                componentContext = componentContext,
                onPush = onPush
            )
        )
    }

    override fun onSelectTab(index: Int) {
        when (index) {
            0 -> navigation.bringToFront(Config.Feed)
            1 -> navigation.bringToFront(Config.Liked)
            2 -> navigation.bringToFront(Config.Profile)
            else -> navigation.bringToFront(Config.Feed)
        }
    }


    @Serializable
    sealed class Config {
        @Serializable
        data object Feed : Config()

        @Serializable
        data object Liked : Config()

        @Serializable
        data object Profile : Config()

    }
}