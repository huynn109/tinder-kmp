package com.huynn109.kmp.tinder.feature.feed.domain.entity

import org.jetbrains.compose.resources.DrawableResource
import tinder.composeapp.generated.resources.Res
import tinder.composeapp.generated.resources.g1
import tinder.composeapp.generated.resources.g2
import tinder.composeapp.generated.resources.g3
import tinder.composeapp.generated.resources.g4
import tinder.composeapp.generated.resources.g5
import tinder.composeapp.generated.resources.g6
import tinder.composeapp.generated.resources.g7

data class FeedProfile(
    val id: Int,
    val name: String,
    val age: Int,
    val gender: Gender = Gender.UNKNOWN,
    val pictures: List<DrawableResource>
) {
    companion object {
        val mock = FeedProfile(
            id = 1,
            name = "Maria",
            age = 18,
            gender = Gender.FEMALE,
            pictures = mutableListOf(
                Res.drawable.g1,
                Res.drawable.g2,
                Res.drawable.g3,
            )
        )

        val mockList = mutableListOf(
            FeedProfile(
                id = 1,
                name = "Maria",
                age = 18,
                gender = Gender.FEMALE,
                pictures = mutableListOf(
                    Res.drawable.g1,
                    Res.drawable.g2,
                    Res.drawable.g3,
                )
            ),
            FeedProfile(
                id = 1,
                name = "Mario",
                age = 19,
                gender = Gender.FEMALE,
                pictures = mutableListOf(
                    Res.drawable.g4,
                    Res.drawable.g5,
                    Res.drawable.g6,
                )
            ),
            FeedProfile(
                id = 1,
                name = "Mariu",
                age = 19,
                gender = Gender.FEMALE,
                pictures = mutableListOf(
                    Res.drawable.g7,
                    Res.drawable.g2,
                    Res.drawable.g3,
                )
            )
        )
    }
}

enum class Gender {
    MALE, FEMALE, UNKNOWN
}
