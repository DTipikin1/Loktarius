package com.loktarius.presentation.timers.stopwatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
@HiltViewModel
class StopwatchViewModel @Inject constructor(): ViewModel() {

    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var startingTime : Long = 0
    var endingTime : Long = 0
    var duration: Long = 0

    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")
    var isPlaying by mutableStateOf(false)

    fun start() {
        startingTime = System.currentTimeMillis()
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

    fun save(tag: Tag): Activity {
        endingTime = System.currentTimeMillis()
        var activity =  if (tag.id != null) Activity(
            id = 0,
            startingTime = startingTime,
            endingTime = endingTime,
            tagId = tag.id
        )
        else Activity(
            id = -1,
            startingTime = startingTime,
            endingTime = endingTime,
            tagId = -1
        )
        resetStopwatch()
        return activity
    }

    fun stop() {
        resetStopwatch()
    }

    private fun resetStopwatch() {
        timer.cancel()
        isPlaying = false
        time = Duration.ZERO
        updateTimeStates()
        startingTime = 0
        endingTime = 0

    }
}