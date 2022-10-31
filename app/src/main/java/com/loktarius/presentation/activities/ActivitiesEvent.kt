package com.loktarius.presentation.activities

import com.loktarius.domain.model.Activity


sealed class ActivitiesEvent {
    data class CreateActivity(val activity: Activity): ActivitiesEvent()
}