package com.huynn109.kmp.tinder.di

import com.huynn109.kmp.tinder.feature.feed.presentation.FeedViewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        FeedViewModel()
    }
}