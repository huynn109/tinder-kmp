package com.huynn109.kmp.tinder.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import com.huynn109.kmp.tinder.utils.getScreenHeight
import com.huynn109.kmp.tinder.utils.getScreenWidth

/**
 * Enum class representing the possible swiping directions.
 */
enum class SwipingDirection {
    Left, Right, Up, Down
}

/**
 * A composable function that remembers the state of a swipe card.
 *
 * @return A [SwipeCardState] object containing the screen width and height.
 */
@Composable
fun rememberSwipeCardState(): SwipeCardState {
    val screenWidth = with(LocalDensity.current) {
        getScreenWidth().toPx()
    }
    val screenHeight = with(LocalDensity.current) {
        getScreenHeight().toPx()
    }
    return remember {
        SwipeCardState(screenWidth, screenHeight)
    }
}

/**
 * Class representing the state of a swipe card.
 *
 * @property maxWidth The maximum width of the card.
 * @property maxHeight The maximum height of the card.
 */
class SwipeCardState(
    internal val maxWidth: Float,
    internal val maxHeight: Float,
) {
    val offset = Animatable(offset(0f, 0f), Offset.VectorConverter)

    /**
     * The [SwipingDirection] the card was swiped at.
     *
     * Null value means the card has not been swiped fully yet.
     */
    var swipedDirection: SwipingDirection? by mutableStateOf(null)
        private set

    /**
     * Resets the card's position to the initial state.
     */
    internal suspend fun reset() {
        offset.animateTo(offset(0f, 0f), tween(400))
    }

    /**
     * Swipes the card in the specified direction with an optional animation specification.
     *
     * @param direction The direction to swipe the card.
     * @param animationSpec The animation specification to use for the swipe.
     */
    suspend fun swipe(
        direction: SwipingDirection,
        animationSpec: AnimationSpec<Offset> = tween(400)
    ) {
        val endX = maxWidth * 1.5f
        val endY = maxHeight
        when (direction) {
            SwipingDirection.Left -> offset.animateTo(offset(x = -endX), animationSpec)
            SwipingDirection.Right -> offset.animateTo(offset(x = endX), animationSpec)
            SwipingDirection.Up -> offset.animateTo(offset(y = -endY), animationSpec)
            SwipingDirection.Down -> offset.animateTo(offset(y = endY), animationSpec)
        }
        this.swipedDirection = direction
    }

    /**
     * Creates an [Offset] object with the specified x and y values.
     *
     * @param x The x-coordinate of the offset.
     * @param y The y-coordinate of the offset.
     * @return An [Offset] object with the specified coordinates.
     */
    private fun offset(x: Float = offset.value.x, y: Float = offset.value.y): Offset {
        return Offset(x, y)
    }

    /**
     * Drags the card to the specified x and y coordinates.
     *
     * @param x The x-coordinate to drag the card to.
     * @param y The y-coordinate to drag the card to.
     */
    internal suspend fun drag(x: Float, y: Float) {
        offset.animateTo(offset(x, y))
    }
}