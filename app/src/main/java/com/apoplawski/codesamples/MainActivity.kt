package com.apoplawski.codesamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apoplawski.codesamples.articles.permissions.PermissionRequester
import com.apoplawski.codesamples.ui.theme.CodeSamplesTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val notificationsPermissionRequester: PermissionRequester by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationsPermissionRequester.initialize(this)

        setContent {
            CodeSamplesTheme {
                NavHost()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeSamplesTheme {
        Greeting("Android")
    }
}