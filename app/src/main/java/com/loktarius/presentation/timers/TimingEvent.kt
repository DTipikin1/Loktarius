package com.loktarius.presentation.timers


sealed class TimingEvent {
    data class ChangeTiming(val changeTo: String): TimingEvent()
}
