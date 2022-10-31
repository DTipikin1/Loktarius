package com.loktarius.data.repository

import com.loktarius.data.data_source.ActivityDao
import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag
import com.loktarius.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    private val dao: ActivityDao
): ActivityRepository{
    override fun getTags(): Flow<List<Tag>> {
        return dao.getTags()
    }

    override suspend fun getTagById(id: Int): Tag? {
        return dao.getTagById(id)
    }

    override suspend fun insertTag(tag: Tag) {
        return dao.insertTag(tag)
    }

    override suspend fun deleteTag(tag: Tag) {
        return dao.deleteTag(tag)
    }

    override  fun getLastUsedTag(): Flow<Tag?> {
        return dao.getLastUsedTag()
    }

    override fun getActivities(): Flow<List<Activity>> {
        return dao.getActivities()
    }

    override suspend fun insertActivity(activity: Activity) {
        return dao.insertActivity(activity)
    }

    override suspend fun deleteActivity(activity: Activity) {
        return dao.deleteActivity(activity)
    }

}