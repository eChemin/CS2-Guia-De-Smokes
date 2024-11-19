package com.example.cs2smoke.list_item

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cs2smoke.model.Smoke
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun SmokeItem(smoke: Smoke) {
    val context = LocalContext.current
    val imagemSmoke = smoke.imgSmoke
    val smokeName = smoke.smokeName
    val smokeDescription = smoke.smokeDescription

    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    var showVideoPlayer by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black).padding(0.dp, 10.dp, 0.dp, 10.dp)
    ) {
        val (imgSmoke, txtSmokeName, txtSmokeDescription, btAddCart, containerImg, rowItem) = createRefs()

        Card(
            modifier = Modifier
                .constrainAs(containerImg) {
                    top.linkTo(parent.top, 0.dp)
                    start.linkTo(parent.start, 0.dp)
                }
                .size(130.dp)
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = ShapeDefaults.Medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
        }

        Image(
            painter = painterResource(imagemSmoke!!),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imgSmoke) {
                    top.linkTo(containerImg.top, 0.dp)
                    start.linkTo(containerImg.start, 0.dp)
                    end.linkTo(containerImg.end, 0.dp)
                    bottom.linkTo(containerImg.bottom, 0.dp)
                }
                .size(70.dp)
        )

        Text(
            text = smokeName!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(txtSmokeName) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(containerImg.end, 0.dp)
            }
        )

        Text(
            text = smokeDescription!!,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(txtSmokeDescription) {
                top.linkTo(txtSmokeName.bottom)
                start.linkTo(containerImg.start, 65.dp)
            }.padding(65.dp, 10.dp, 40.dp, 0.dp),
            maxLines = 3
        )

        Button(
            onClick = {
                if (showVideoPlayer) {
                    exoPlayer.pause()
                    showVideoPlayer = false
                } else {
                    val videoUri = Uri.parse("android.resource://${context.packageName}/raw/${smoke.videoFileName}")
                    val mediaItem = MediaItem.fromUri(videoUri)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.prepare()
                    exoPlayer.play()
                    showVideoPlayer = true
                }
            },
            modifier = Modifier
                .height(45.dp)
                .constrainAs(btAddCart) {
                    top.linkTo(txtSmokeDescription.bottom, 10.dp)
                    start.linkTo(txtSmokeDescription.start, 60.dp)
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF008000)
            )
        ) {
            Text(
                text = if (showVideoPlayer) "Fechar Tutorial" else "Tutorial",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (showVideoPlayer) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(rowItem) {
                        top.linkTo(txtSmokeDescription.bottom, 65.dp)
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                    }
            )
        }
    }
}
