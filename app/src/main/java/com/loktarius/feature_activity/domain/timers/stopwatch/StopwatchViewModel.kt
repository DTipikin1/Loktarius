package com.loktarius.feature_activity.domain.timers.stopwatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
class StopwatchViewModel: ViewModel() {

    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")
    var isPlaying by mutableStateOf(false)

    fun start() {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            time = time.plus(Duration.seconds(1))
            updateTimeStates()
        }
        isPlaying = true
    }
    private fun updateTimeStates() {
        time.toComponents { hours, minutes, seconds, _ ->
            this@StopwatchViewModel.seconds = seconds.pad()
            this@StopwatchViewModel.minutes = minutes.pad()
            this@StopwatchViewModel.hours= hours.pad()
        }
    }

    private fun Int.pad(): String {
        return this.toString().padStart(2,'0')
    }

    fun pause() {
        timer.cancel()
        isPlaying = false
    }

    fun stop() {
        pause()
        time = Duration.ZERO
        updateTimeStates()
    }
}