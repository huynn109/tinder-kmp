package com.huynn109.kmp.tinder.feature.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.huynn109.kmp.tinder.feature.feed.FeedComponent
import com.huynn109.kmp.tinder.feature.liked.LikedComponent
import com.huynn109.kmp.tinder.feature.profile.ProfileComponent

interface MainComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child(val index: Int) {
        data class Feed(val component: FeedComponent) : Child(index = 0)
        data class Liked(val component: LikedComponent) : Child(index = 1)
        data class Profile(val component: ProfileComponent) : Child(index = 2)
    }

    fun onSelectTab(index: Int)
}