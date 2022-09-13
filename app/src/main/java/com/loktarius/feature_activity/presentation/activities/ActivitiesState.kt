package com.loktarius.feature_activity.presentation.activities

import com.loktarius.feature_activity.domain.model.Activity

data class ActivitiesState(
    val activities: List<Activity> = emptyList()
)

