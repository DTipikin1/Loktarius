package com.loktarius.feature_activity.domain.use_case

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository

class GetLastUsedTag(
    val repository: ActivityRepository
) {

    suspend operator fun invoke(): Tag? {
        return repository.getLastUsedTag()
    }
}