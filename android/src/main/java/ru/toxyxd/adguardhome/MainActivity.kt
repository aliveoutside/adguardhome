package ru.toxyxd.adguardhome

import android.os.Bundle
import androidx.compose.material3.MaterialTheme
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import org.koin.core.component.KoinComponent
import ru.toxyxd.common.App

class MainActivity : PreComposeActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App()
            }
        }
    }
}