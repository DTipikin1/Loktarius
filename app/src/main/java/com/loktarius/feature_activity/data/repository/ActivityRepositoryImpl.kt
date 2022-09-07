package com.loktarius.feature_activity.data.repository

import com.loktarius.feature_activity.data.data_source.ActivityDao
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository
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

    override suspend fun getLastUsedTag(): Tag? {
        return dao.getLastUsedTag()
    }
}