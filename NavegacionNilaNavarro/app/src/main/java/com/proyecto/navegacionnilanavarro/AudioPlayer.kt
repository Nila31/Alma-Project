package com.proyecto.navegacionnilanavarro

import android.media.MediaPlayer

class AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun initialize(audioUrl: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioUrl)
        mediaPlayer?.prepare()
    }

    fun play() {
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}