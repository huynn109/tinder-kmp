package com.huynn109.kmp.tinder.feature.feed.presentation

import androidx.lifecycle.ViewModel
import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FeedViewModel : ViewModel() {

    var mockList = List(5) { FeedProfile.mockList }.flatten()

    private val _uiFeedState: MutableStateFlow<FeedState> = MutableStateFlow(FeedState.Loading)
    val uiFeedState: StateFlow<FeedState> = _uiFeedState

    fun dispatch(feedEvent: FeedEvent) {
        when (val event = feedEvent) {
            is FeedEvent.SwipeUser -> {
                Napier.d("Swipe user: ${event}, isSwipeRight: ${event.direction}")
            }

            FeedEvent.RemoveLastProfile -> {
                _uiFeedState.update {
                    if (it is FeedState.Success) {
                        it.copy(
                            profileList = it.profileList.dropLast(1)
                        )
                    } else it
                }
            }

            FeedEvent.LoadFeedProfile -> {
                _uiFeedState.update {
                    FeedState.Success(profileList = mockList)
                }
            }
        }
    }
}