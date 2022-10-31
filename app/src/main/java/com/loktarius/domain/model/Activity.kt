package com.loktarius.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tagId: Int,
    val startingTime: Long,
    val endingTime: Long
)