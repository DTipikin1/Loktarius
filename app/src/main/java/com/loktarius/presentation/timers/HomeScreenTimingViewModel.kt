package com.loktarius.presentation.timers

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenTimingViewModel @Inject constructor(

) : ViewModel() {
    private val _state = mutableStateOf(HomeScreenTimingState())
    val state: State<HomeScreenTimingState> = _state


    fun onEvent(event: TimingEvent) {
        when(event) {
            is TimingEvent.ChangeTiming -> {
                viewModelScope.launch {
                    if (event.changeTo == "Timer") {
                        _state.value.timer = true
                        _state.value.stopwatch = false
                    } else {
                        _state.value.timer = false
                        _state.value.stopwatch = true
                    }

                }
            }
        }
    }
}