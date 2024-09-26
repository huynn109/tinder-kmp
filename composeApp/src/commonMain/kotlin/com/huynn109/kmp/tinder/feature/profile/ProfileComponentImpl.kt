package com.huynn109.kmp.tinder.feature.profile

import com.arkivanov.decompose.ComponentContext
import com.huynn109.kmp.tinder.feature.feed.FeedComponent
import com.huynn109.kmp.tinder.feature.feed.FeedComponentImpl
import com.huynn109.kmp.tinder.navigation.Page

fun buildProfileComponent(
    componentContext: ComponentContext,
    onPush: (Page) -> Unit,
): ProfileComponent = ProfileComponentImpl()

class ProfileComponentImpl: ProfileComponent