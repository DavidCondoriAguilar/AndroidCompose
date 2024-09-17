package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                SplashActivity()
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    // Remember states for username and password
    val (username, setUsername) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (loginMessage, setLoginMessage) = remember { mutableStateOf("") }

    val logo: Painter = painterResource(id = R.drawable.logo) // Replace with your drawable resource

    // Obtain the context for starting an activity
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)), // Background color and corner radius
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = logo,
                contentDescription = null, // No content description needed for the logo
                modifier = Modifier
                    .size(170.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)) // Background color and corner radius for the logo
                    .padding(16.dp)
            )
        }

        // Title Text
        Text(
            text = stringResource(id = R.string.author),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp) // Space below the title
        )

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { setUsername(it) },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // Space below the username field
        )

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { setPassword(it) },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp), // Space below the password field
            visualTransformation = PasswordVisualTransformation() // Hide password input
        )

        // Login Button
        Button(
            onClick = {
                if (username == "deivdev" && password == "deivdev") {
                    setLoginMessage("Login Successful")
                    // Start HomeActivity on successful login
                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                } else {
                    setLoginMessage("Invalid Username or Password")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // Space below the button
            shape = RoundedCornerShape(12.dp) // Rounded corners for the button
        ) {
            Text(text = "Login")
        }

        // Login Message
        if (loginMessage.isNotEmpty()) {
            Text(
                text = loginMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp) // Space above the message
            )
        }
    }
}
