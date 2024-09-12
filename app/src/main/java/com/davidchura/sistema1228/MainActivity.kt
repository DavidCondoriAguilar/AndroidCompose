package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(top = 24.dp),
                        .padding(top = dimensionResource(id = R.dimen.size_3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(text = stringResource(id = R.string.phrase),
                        style = MaterialTheme.typography.displaySmall)
                    Text(text = stringResource(id = R.string.author))
                }
                Column( modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = R.string.greeting),
                        //style = TextStyle(fontSize = 72.sp, fontWeight = FontWeight.W200)
                        style = MaterialTheme.typography.displayLarge)
                    Button(onClick = {
                        startActivity(Intent(this@MainActivity,
                            BeginActivity::class.java))
                    }) {
                        Text(text = stringResource(id = R.string.begin))
                    }

                }//Column
                Box( modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = dimensionResource(id = R.dimen.size_2)),
                    contentAlignment = Alignment.BottomCenter){
                   Text(text = stringResource(id = R.string.rights))
                }
            }
        }
    }
}
