package com.huynn109.kmp.tinder.feature.feed

import com.arkivanov.decompose.ComponentContext
import com.huynn109.kmp.tinder.navigation.Page


fun buildFeedComponent(
    componentContext: ComponentContext,
    onPush: (Page) -> Unit,
): FeedComponent = FeedComponentImpl()

class FeedComponentImpl : FeedComponent {

}