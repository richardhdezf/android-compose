package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app.ui.PhoneContactsNavGraph
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

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidComposeAppTheme {
        Greeting("Android")
    }
}*/
