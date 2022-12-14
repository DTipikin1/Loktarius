package com.loktarius.domain.repository

import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    fun getTags(): Flow<List<Tag>>

    suspend fun getTagById(id: Int): Tag?

    suspend fun insertTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)

    fun getLastUsedTag(): Flow<Tag?>

    fun getActivities(): Flow<List<Activity>>

    suspend fun insertActivity(activity: Activity)

    suspend fun deleteActivity(activity: Activity)

}