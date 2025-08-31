package com.example.mvvmsongagain.adapter

object FormatDuration {
     fun format(duration: Int): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}