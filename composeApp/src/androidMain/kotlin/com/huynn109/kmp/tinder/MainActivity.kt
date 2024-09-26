package com.huynn109.kmp.tinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.huynn109.kmp.tinder.navigation.TinderRootComponent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootComponent = TinderRootComponent(
            componentContext = defaultComponentContext()
        )
        setContent {
            Napier.base(DebugAntilog())
            AppWithKoin(rootComponent)
        }
    }
}