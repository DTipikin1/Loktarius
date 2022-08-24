package com.loktarius.feature_activity.domain.repository

import com.loktarius.feature_activity.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    fun getTags(): Flow<List<Tag>>

    suspend fun getTagById(id: Int): Tag?

    suspend fun insertTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)
}