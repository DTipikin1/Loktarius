package com.loktarius.feature_activity.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    val name: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)

class InvalidTagException(message: String): Exception(message)
