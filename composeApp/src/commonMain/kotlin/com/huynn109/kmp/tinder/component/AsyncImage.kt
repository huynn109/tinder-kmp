package com.huynn109.kmp.tinder.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.size.Dimension
import coil3.size.Size

/**
 * A composable function that loads and displays an image from a URL asynchronously.
 *
 * @param url The URL of the image to load.
 * @param contentDescription The content description for the image.
 * @param modifier The modifier to be applied to the image.
 * @param contentScale The scaling strategy to be used for the image.
 * @param size The size of the image to be loaded.
 * @param backgroundColor The background color to be applied behind the image.
 * @param error The painter to be used if the image request fails.
 */
@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    size: Size = Size(Dimension.Undefined, 500),
    backgroundColor: Color? = null,
    error: Painter? = null,
) {
    val context = LocalPlatformContext.current
    val imageRequest = remember(url, size) {
        ImageRequest.Builder(context)
            .data(url)
            .size(size)
            .build()
    }

    val backgroundColorModifier =
        if (backgroundColor != null) {
            Modifier.background(color = backgroundColor)
        } else {
            Modifier
        }

    Box(modifier.then(backgroundColorModifier)) {
        coil3.compose.AsyncImage(
            model = imageRequest,
            contentDescription = contentDescription,
            modifier = Modifier.matchParentSize(),
            contentScale = contentScale,
            error = error,
        )
    }
}