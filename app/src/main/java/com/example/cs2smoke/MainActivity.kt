package com.example.cs2smoke

import android.os.Bundle
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.cs2smoke.list_item.SmokeItem
import com.example.cs2smoke.model.Smoke

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmokeList()
        }
    }
}
@Composable
private fun SmokeList(){

    val smokeList: MutableList<Smoke> = mutableListOf(
        Smoke(
            imgSmoke = R.drawable.imgcabecinha,
            smokeName = "Smoke Cabeçinha",
            smokeDescription = "Parado + Jumpthrow",
            videoFileName = "smokecabecinha"
        ),
        Smoke(
            imgSmoke = R.drawable.imgct,
            smokeName = "Smoke Base CT",
            smokeDescription = "Parado + Jumpthrow",
            videoFileName = "somkect"
        ),
        Smoke(
            imgSmoke = R.drawable.imgpassagem,
            smokeName = "Smoke Passagem A",
            smokeDescription = "Parado",
            videoFileName = "smokepassagem"
        ),
        Smoke(
            imgSmoke = R.drawable.imgliga,
            smokeName = "Smoke Ligação",
            smokeDescription = "Parado + Jumpthrow",
            videoFileName = "smokeliga"
        ),
        Smoke(
            imgSmoke = R.drawable.imgjanelao,
            smokeName = "Smoke Janelão",
            smokeDescription = "Andando pra direita + Jumpthrow",
            videoFileName = "smokejanelao"
        ),
        Smoke(
            imgSmoke = R.drawable.imgpassagemmeio,
            smokeName = "Passagem Meio",
            smokeDescription = "Parado",
            videoFileName = "somkepassagemmeio"
        ),
        Smoke(
            imgSmoke = R.drawable.imgl,
            smokeName = "Smoke L",
            smokeDescription = "Parado",
            videoFileName = "somkel"
        ),
        Smoke(
            imgSmoke = R.drawable.imgjenalab,
            smokeName = "Smoke Janela B",
            smokeDescription = "Parado + Jumpthrow",
            videoFileName = "smokejanelab"
        ),
        Smoke(
            imgSmoke = R.drawable.imgportab,
            smokeName = "Smoke Porta B",
            smokeDescription = "Parado + Jumpthrow",
            videoFileName = "somokeportab"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black)
    ){
        LazyColumn{
            itemsIndexed(smokeList){position,smoke ->
                SmokeItem(smoke)
            }
        }

    }
}
