package com.huynn109.kmp.tinder.feature.feed.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huynn109.kmp.tinder.component.AsyncImage
import com.huynn109.kmp.tinder.feature.feed.domain.entity.FeedProfile
import com.huynn109.kmp.tinder.utils.asPainter
import com.huynn109.kmp.tinder.utils.clickableWithoutRipple

@Composable
fun FeedCard(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    feedProfile: FeedProfile = FeedProfile.mock
) {
    var currentIndex by remember { mutableStateOf(0) }

    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            .68f to Color.Transparent,
            .92f to Color.Black
        )
    )

    Card(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(.6f),
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            //Picture
            val picture = feedProfile.pictures[currentIndex]
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                painter = picture.asPainter(),
            )

            //Gradient
            Spacer(
                Modifier
                    .fillMaxSize()
                    .background(gradient)
            )
            Box(contentModifier.fillMaxSize()) {
                //Upper picture index indicator
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    repeat(feedProfile.pictures.size) { index ->
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .padding(horizontal = 4.dp)
                                .alpha(if (index == currentIndex) 1f else .5f)
                                .background(if (index == currentIndex) Color.White else Color.LightGray)
                        )
                    }
                }
                //Clickable
                Row(Modifier.fillMaxSize()) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickableWithoutRipple { if (currentIndex > 0) currentIndex-- })
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickableWithoutRipple { if (currentIndex < feedProfile.pictures.size - 1) currentIndex++ }
                    )
                }
                //Information
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = feedProfile.name,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = feedProfile.age.toString(), color = Color.White, fontSize = 28.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Default.Info,
                        tint = Color.White,
                        contentDescription = null
                    )
                }

            }
        }
    }
}