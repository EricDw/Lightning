package com.dewildte.lightning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dewildte.lightning.application.LightningApplicationController
import com.dewildte.lightning.application.api.LightningApplication

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val model = application as LightningApplication

            LightningApplicationController(
                model = model
            )

        }
    }
}