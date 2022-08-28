package com.loktarius.feature_activity.domain.use_case

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository

class DeleteTag(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(tag: Tag) {
        repository.deleteTag(tag)
    }
}