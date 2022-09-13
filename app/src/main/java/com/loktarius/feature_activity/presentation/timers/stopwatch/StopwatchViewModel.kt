package com.loktarius.feature_activity.presentation.timers.stopwatch

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.use_case.activities.ActivityUseCases
import com.loktarius.feature_activity.presentation.activities.ActivitiesEvent
import com.loktarius.feature_activity.presentation.activities.HomeScreenActivityViewModel
import com.loktarius.feature_activity.presentation.tags.HomeScreenTagsViewModel
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

    fun save() {
        endingTime = System.currentTimeMillis() - startingTime

            Log.d("ACTIVITY ADDED","test")
        resetStopwatch()
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