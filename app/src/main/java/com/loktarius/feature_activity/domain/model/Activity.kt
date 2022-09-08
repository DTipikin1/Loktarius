package com.loktarius.feature_activity.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Activity(
    @PrimaryKey val id: Int,
    val tagId: Int,
    val startingTime: Long,
    val endingTime: Long
)