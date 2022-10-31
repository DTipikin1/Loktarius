package com.loktarius.domain.use_case.activities

import com.loktarius.domain.model.Activity
import com.loktarius.domain.repository.ActivityRepository

class DeleteActivity(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(activity: Activity) {
        activityRepository.deleteActivity(activity)
    }
}