package com.loktarius.feature_activity.domain.use_case.activities

import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.repository.ActivityRepository

class DeleteActivity(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(activity: Activity) {
        activityRepository.deleteActivity(activity)
    }
}