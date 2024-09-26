package com.huynn109.kmp.tinder.feature.feed.presentation

import com.huynn109.kmp.tinder.component.SwipingDirection
import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile

sealed class FeedEvent {
    class SwipeUser(val direction: SwipingDirection,val profile: FeedProfile) : FeedEvent()
    data object RemoveLastProfile : FeedEvent()
    data object LoadFeedProfile : FeedEvent()
}