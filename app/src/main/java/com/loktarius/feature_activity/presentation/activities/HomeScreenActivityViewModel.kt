package com.loktarius.feature_activity.presentation.activities

import android.app.PendingIntent.getActivities
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loktarius.feature_activity.domain.use_case.activities.ActivityUseCases
import com.loktarius.feature_activity.domain.util.TagOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenActivityViewModel @Inject constructor(
    private val activityUseCases: ActivityUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ActivitiesState())
    val state: State<ActivitiesState> = _state

    private var getActivitiesJob: Job? = null


    init {
        getActivities()
    }
    fun onEvent(event: ActivitiesEvent) {
        when(event) {
            is ActivitiesEvent.CreateActivity -> {
                viewModelScope.launch {
                    activityUseCases.addActivity(event.activity)
                    Log.d("ACTIVITY ADDED",event.activity.toString())
                }
            }
        }
    }

    private fun getActivities() {
        getActivitiesJob?.cancel()
        getActivitiesJob = activityUseCases.getActivities()
            .onEach { activities ->
                _state.value = state.value.copy(
                    activities = activities,
                )
            }
            .launchIn(viewModelScope)
    }
}