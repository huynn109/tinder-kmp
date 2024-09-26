package com.huynn109.kmp.tinder.di

import org.koin.core.module.Module
import org.koin.dsl.module


val sharedModule: Module
    get() = module {
        includes(presentationModule)
    }
