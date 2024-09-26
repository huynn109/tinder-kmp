package com.huynn109.kmp.tinder.feature.feed.presentation

import androidx.compose.runtime.Immutable
import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile

@Immutable
data class FeedProfileState(val feedProfile: FeedProfile)