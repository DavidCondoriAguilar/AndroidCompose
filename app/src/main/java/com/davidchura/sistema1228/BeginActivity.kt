package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

class BeginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                Column {
                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.begin_image),
                            modifier = Modifier.height(300.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(id = R.string.begin_image_description)
                        )
                        Text(
                            text = stringResource(id = R.string.begin),
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.size_2))
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = dimensionResource(id = R.dimen.size_3)),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.begin),
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(text = stringResource(id = R.string.welcome_text))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                startActivity(Intent(this@BeginActivity, TermsActivity::class.java))
                            }) {
                                Text(text = stringResource(id = R.string.terms))
                            }
                            Button(onClick = {
                                startActivity(Intent(this@BeginActivity, HomeActivity::class.java))
                            }) {
                                Text(text = stringResource(id = R.string.home))
                            }
                        }//Row
                    }//Column
                }
            }
        }
    }
}
