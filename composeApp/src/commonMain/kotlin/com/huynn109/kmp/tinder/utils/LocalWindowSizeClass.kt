package com.huynn109.kmp.tinder.utils

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalWindowSizeClass =
  staticCompositionLocalOf<WindowSizeClass> { error("No WindowSizeClass found") }