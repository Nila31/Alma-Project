package com.proyecto.navegacionnilanavarro.Views

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class VideoPlayerManager(private val context: Context) {
    private val player: SimpleExoPlayer by lazy {
        SimpleExoPlayer.Builder(context).build()
    }

    fun initializePlayer(videoPath: String) {
        val mediaSource = ProgressiveMediaSource.Factory(DefaultDataSourceFactory(context)).createMediaSource(Uri.parse(videoPath))
        player.setMediaSource(mediaSource)
        player.prepare()
    }

    fun releasePlayer() {
        player.release()
    }

    fun getPlayerView(): PlayerView {
        return PlayerView(context).apply {
            player = this@VideoPlayerManager.player
        }
    }
}


