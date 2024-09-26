package com.huynn109.kmp.tinder.feature.feed.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.huynn109.kmp.tinder.component.SwipingDirection
import com.huynn109.kmp.tinder.component.rememberSwipeCardState
import com.huynn109.kmp.tinder.component.swipeCard
import com.huynn109.kmp.tinder.feature.feed.FeedComponent
import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import tinder.composeapp.generated.resources.Res
import tinder.composeapp.generated.resources.app_name
import tinder.composeapp.generated.resources.no_more_profiles

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    component: FeedComponent,
    viewModel: FeedViewModel = koinInject<FeedViewModel>()
) {
    viewModel.dispatch(FeedEvent.LoadFeedProfile)
    val uiFeedState = viewModel.uiFeedState.collectAsState()

    FeedContent(
        modifier = modifier,
        uiFeedState = uiFeedState,
        swipeUser = { profile, swipingDirection ->
            Napier.d("Swipe user: $profile, isSwipeRight: $swipingDirection")
            viewModel.dispatch(FeedEvent.SwipeUser(swipingDirection, profile))
        },
        removeLastProfile = {
            Napier.d("Remove last profile")
            viewModel.dispatch(FeedEvent.RemoveLastProfile)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedContent(
    modifier: Modifier = Modifier,
    uiFeedState: State<FeedState>,
    swipeUser: (FeedProfile, SwipingDirection) -> Unit,
    removeLastProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.h6
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) {
        when (val state = uiFeedState.value) {
            is FeedState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is FeedState.Success -> {
                val swipeStates = state.profileList.map { rememberSwipeCardState() }
                state.profileList.forEachIndexed { index, profile ->
                    FeedCard(
                        modifier = Modifier.swipeCard(
                            state = swipeStates[index],
                            onSwiped = { swipingDirection ->
                                swipeUser(
                                    profile,
                                    swipingDirection
                                )
                                removeLastProfile.invoke()
                            }
                        ),
                        feedProfile = profile,
                    )
                }
            }

            is FeedState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }

            FeedState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.no_more_profiles),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}