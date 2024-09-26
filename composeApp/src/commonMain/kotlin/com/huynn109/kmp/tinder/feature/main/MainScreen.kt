package com.huynn109.kmp.tinder.feature.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.huynn109.kmp.tinder.feature.Screen
import com.huynn109.kmp.tinder.feature.feed.presentation.FeedScreen
import com.huynn109.kmp.tinder.feature.liked.presentation.LikedScreen
import com.huynn109.kmp.tinder.feature.profile.presentation.ProfileScreen
import com.huynn109.kmp.tinder.utils.asPainter
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    component: MainComponent,
) {
    val bottomNavItems =
        remember {
            persistentListOf(
                Screen.Feed,
                Screen.Liked,
                Screen.Profile,
            )
        }
    val childStack by component.childStack.subscribeAsState()
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        selected = screen.index == childStack.active.instance.index,
                        onClick = { component.onSelectTab(screen.index) },
                        icon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = screen.icon.asPainter(),
                                contentDescription = screen.label
                            )
                        },
                        label = {
                            Text(text = screen.label)
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        Children(
            modifier = Modifier.then(modifier)
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
            stack = childStack,
        ) {
            when (val child = it.instance){
                is MainComponent.Child.Feed -> FeedScreen(component = child.component)
                is MainComponent.Child.Liked -> LikedScreen(component = child.component)
                is MainComponent.Child.Profile -> ProfileScreen(component = child.component)
            }
        }
    }
}
