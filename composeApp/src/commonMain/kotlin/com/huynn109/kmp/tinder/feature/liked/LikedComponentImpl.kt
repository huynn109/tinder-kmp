package com.huynn109.kmp.tinder.feature.liked

import com.arkivanov.decompose.ComponentContext
import com.huynn109.kmp.tinder.feature.feed.FeedComponent
import com.huynn109.kmp.tinder.feature.feed.FeedComponentImpl
import com.huynn109.kmp.tinder.navigation.Page

fun buildLikedComponent(
    componentContext: ComponentContext,
    onPush: (Page) -> Unit,
): LikedComponent = LikedComponentImpl()

class LikedComponentImpl: LikedComponent