package com.loktarius.feature_activity.domain.use_case.tags

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetLastUsedTag(
    val repository: ActivityRepository
) {

     operator fun invoke(): Flow<Tag?> {
        return repository.getLastUsedTag()
    }
}