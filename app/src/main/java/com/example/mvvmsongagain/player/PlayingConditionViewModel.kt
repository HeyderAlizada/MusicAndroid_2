package com.example.mvvmsongagain.player

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayingConditionViewModel : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null
    private val handler = Handler(Looper.getMainLooper())

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    private val _duration = MutableLiveData(0)
    val duration: LiveData<Int> get() = _duration

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> get() = _progress



    private val updateProgressRunnable = object : Runnable {
        override fun run() {
            mediaPlayer?.let { mp ->
                _progress.value = mp.currentPosition
                handler.postDelayed(this, 500)
            }
        }
    }

    fun playPause(previewUrl: String) {
        if (mediaPlayer == null) {
            initMediaPlayer(previewUrl)
        } else {
            if (mediaPlayer?.isPlaying == true) {
                pause()
            } else {
                play()
            }
        }
    }

    private fun initMediaPlayer(previewUrl: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(previewUrl)
            prepareAsync()
            setOnPreparedListener { mp ->
                _duration.value = mp.duration
                play()
            }
        }
    }

    private fun play() {
        mediaPlayer?.start()
        _isPlaying.value = true
        handler.post(updateProgressRunnable)
    }

    private fun pause() {
        mediaPlayer?.pause()
        _isPlaying.value = false
        handler.removeCallbacks(updateProgressRunnable)
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }

    fun releasePlayer() {
        handler.removeCallbacks(updateProgressRunnable)
        mediaPlayer?.release()
        mediaPlayer = null
        _isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }
}
