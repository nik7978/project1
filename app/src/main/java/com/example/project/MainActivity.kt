package com.example.project

import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.project.ui.theme.ProjectTheme
import com.google.gson.Gson
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var i by remember { mutableStateOf(455) }
            var game1 by remember {
                mutableStateOf(
                    gameinfo(

                    )
                )
            }

            Column() {
                Spacer(modifier = Modifier.height(100.dp))

                Button(onClick = {
                    thread {
                        val response = URL("https://www.mmobomb.com/api1/game?id=$i")
                        val text = response.readText()
                        val gameinfo = Gson().fromJson(text, gameinfo::class.java)
                        game1 = gameinfo
                        i += 1
                    }
                }) {
                    Text("facts")
                }
                Greeting(
                    gameinfo = game1
                )

            }
        }
    }


    data class gameinfo(
        var id: Int? = null,
        var title: String? = null,
        var status: String? = null,
        var shortDescription: String? = null,
        var description: String? = null,
        var genre: String? = null,
        var image: String? = null
    )

    @Composable
    fun Greeting(gameinfo: gameinfo) {
        Column() {
            Text(
                text = gameinfo.id.toString(),
                fontSize = 20.sp
            )
            Text(
                text = gameinfo.title.toString(),
                fontSize = 20.sp
            )
            Text(
                text = gameinfo.status.toString(),
                fontSize = 20.sp
            )
            Text(
                text = gameinfo.shortDescription.toString(),
                fontSize = 20.sp
            )
            AsyncImage(
                model = gameinfo.image.toString(),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = gameinfo.description.toString(),
                fontSize = 20.sp

            )
            Text(
                text = gameinfo.genre.toString(),
                fontSize = 20.sp
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }
}