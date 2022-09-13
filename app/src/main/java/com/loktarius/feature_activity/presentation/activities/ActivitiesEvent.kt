package com.loktarius.feature_activity.presentation.activities

import com.loktarius.feature_activity.domain.model.Activity


sealed class ActivitiesEvent {
    data class CreateActivity(val activity: Activity): ActivitiesEvent()
}