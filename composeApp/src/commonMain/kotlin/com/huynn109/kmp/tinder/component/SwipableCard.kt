package com.huynn109.kmp.tinder.component

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Enables Tinder-like swiping gestures.
 *
 * @param state The current state of the swipeable card. Use [rememberSwipeableCardState] to create.
 * @param onSwiped Will be called once a swipe gesture is completed. The given [SwipingDirection] will indicate which side the gesture was performed on.
 * @param onSwipeCancel Will be called when the gesture is stopped before reaching the minimum threshold to be treated as a full swipe.
 * @param blockedDirections The directions which will not trigger a swipe. By default, only horizontal swipes are allowed.
 * @return A [Modifier] that enables swipe gestures.
 */
fun Modifier.swipeCard(
    state: SwipeCardState,
    onSwiped: (SwipingDirection) -> Unit,
    onSwipeCancel: () -> Unit = {},
    blockedDirections: List<SwipingDirection> = listOf(SwipingDirection.Down),
) = pointerInput(Unit) {
    coroutineScope {
        detectDragGestures(
            onDragCancel = {
                launch {
                    state.reset()
                    onSwipeCancel()
                }
            },
            onDrag = { change, dragAmount ->
                launch {
                    val original = state.offset.targetValue
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-state.maxWidth, state.maxWidth),
                        y = summed.y.coerceIn(-state.maxHeight, state.maxHeight)
                    )
                    if (change.positionChange() != Offset.Zero) change.consume()
                    state.drag(newValue.x, newValue.y)
                }
            },
            onDragEnd = {
                launch {
                    val coercedOffset = state.offset.targetValue
                        .coerceIn(blockedDirections,
                            maxHeight = state.maxHeight,
                            maxWidth = state.maxWidth)

                    if (hasNotTravelledEnough(state, coercedOffset)) {
                        state.reset()
                        onSwipeCancel()
                    } else {
                        val horizontalTravel = abs(state.offset.targetValue.x)
                        val verticalTravel = abs(state.offset.targetValue.y)

                        if (horizontalTravel > verticalTravel) {
                            if (state.offset.targetValue.x > 0) {
                                state.swipe(SwipingDirection.Right)
                                onSwiped(SwipingDirection.Right)
                            } else {
                                state.swipe(SwipingDirection.Left)
                                onSwiped(SwipingDirection.Left)
                            }
                        } else {
                            if (state.offset.targetValue.y < 0) {
                                state.swipe(SwipingDirection.Up)
                                onSwiped(SwipingDirection.Up)
                            } else {
                                state.swipe(SwipingDirection.Down)
                                onSwiped(SwipingDirection.Down)
                            }
                        }
                    }
                }
            }
        )
    }
}.graphicsLayer {
    translationX = state.offset.value.x
    translationY = state.offset.value.y
    rotationZ = (state.offset.value.x / 60).coerceIn(-40f, 40f)
}

/**
 * Extension function to coerce the offset within the allowed range based on blocked directions.
 *
 * @param blockedDirections The directions which will not trigger a swipe.
 * @param maxHeight The maximum height the card can be swiped.
 * @param maxWidth The maximum width the card can be swiped.
 * @return An [Offset] object with the coerced coordinates.
 */
private fun Offset.coerceIn(
    blockedDirections: List<SwipingDirection>,
    maxHeight: Float,
    maxWidth: Float,
): Offset {
    return copy(
        x = x.coerceIn(
            if (blockedDirections.contains(SwipingDirection.Left)) {
                0f
            } else {
                -maxWidth
            },
            if (blockedDirections.contains(SwipingDirection.Right)) {
                0f
            } else {
                maxWidth
            }
        ),
        y = y.coerceIn(if (blockedDirections.contains(SwipingDirection.Up)) {
            0f
        } else {
            -maxHeight
        },
            if (blockedDirections.contains(SwipingDirection.Down)) {
                0f
            } else {
                maxHeight
            }
        )
    )
}

/**
 * Checks if the card has not travelled enough to be considered a full swipe.
 *
 * @param state The current state of the swipeable card.
 * @param offset The current offset of the card.
 * @return True if the card has not travelled enough, false otherwise.
 */
private fun hasNotTravelledEnough(
    state: SwipeCardState,
    offset: Offset,
): Boolean {
    return abs(offset.x) < state.maxWidth / 3 &&
            abs(offset.y) < state.maxHeight / 3
}