package com.huynn109.kmp.tinder.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tinder.composeapp.generated.resources.Res
import tinder.composeapp.generated.resources.ic50_feed

@Composable
fun DrawableResource?.asPainter(): Painter {
    if (this == null) return painterResource(Res.drawable.ic50_feed)
    return painterResource(this)
}