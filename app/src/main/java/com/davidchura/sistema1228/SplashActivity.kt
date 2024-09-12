package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1228Theme {
                LaunchedEffect(key1 = true) {
                    delay(2000)
                    lifecycleScope.launch {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                }
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
