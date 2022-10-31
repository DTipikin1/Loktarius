package com.loktarius.domain.use_case.activities

data class ActivityUseCases(
    val addActivity: AddActivity,
    val deleteActivity: DeleteActivity,
    val getActivities: GetActivities
)
