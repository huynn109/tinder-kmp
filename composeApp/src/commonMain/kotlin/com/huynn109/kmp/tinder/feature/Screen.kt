package com.huynn109.kmp.tinder.feature

import org.jetbrains.compose.resources.DrawableResource
import tinder.composeapp.generated.resources.Res
import tinder.composeapp.generated.resources.ic30_liked
import tinder.composeapp.generated.resources.ic48_profile
import tinder.composeapp.generated.resources.ic50_feed

sealed class Screen(
    val index: Int,
    val label: String,
    val icon: DrawableResource
) {

    data object Feed : Screen(
        index = 0,
        label = "Feed",
        icon = Res.drawable.ic50_feed
    )

    data object Liked : Screen(
        index = 1,
        label = "Liked me",
        icon = Res.drawable.ic30_liked
    )

    data object Profile : Screen(
        index = 2,
        label = "Profile",
        icon = Res.drawable.ic48_profile
    )
}