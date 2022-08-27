package com.loktarius.feature_activity.domain.use_case

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository

class addTag(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(tag: Tag) {
        if (tag.name.isBlank()) {

        }
    }
}