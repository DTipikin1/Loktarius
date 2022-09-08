package com.loktarius.feature_activity.domain.use_case.activities

import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.repository.ActivityRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetActivities(
    private val repository: ActivityRepository
) {
    operator fun invoke() : Flow<List<Activity>> {
        return repository.getActivities().map { activities ->
            activities.sortedBy { it.id }
        }
    }
}