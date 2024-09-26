package com.huynn109.kmp.tinder.feature.feed.presentation

import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile

sealed class FeedState {
    data object Loading: FeedState()
    data object Empty: FeedState()
    data class Error(val message: String): FeedState()
    data class Success(val profileList: List<FeedProfile>): FeedState()
}