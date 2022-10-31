package com.loktarius.presentation.activities

import com.loktarius.domain.model.Activity

data class ActivitiesState(
    val activities: List<Activity> = emptyList()
)

