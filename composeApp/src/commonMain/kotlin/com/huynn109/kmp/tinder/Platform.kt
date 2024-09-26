package com.huynn109.kmp.tinder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform