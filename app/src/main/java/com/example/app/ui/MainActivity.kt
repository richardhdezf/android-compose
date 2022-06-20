package com.example.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app.MainApplication
import com.example.app.ui.theme.AndroidComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as MainApplication).container
        setContent {
            AndroidComposeAppTheme {
                PhoneContactsNavGraph(appContainer = appContainer)
            }
        }
    }
}
