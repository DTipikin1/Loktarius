package com.loktarius.feature_activity.domain.use_case

import com.loktarius.feature_activity.domain.model.InvalidTagException
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository

class AddTag(
    private val activityRepository: ActivityRepository
) {
    @Throws(InvalidTagException::class)
    suspend operator fun invoke(tag: Tag) {
        if (tag.name.isBlank()) {
            throw InvalidTagException("The name of the tag can't be empty.")
        }
        activityRepository.insertTag(tag)
    }
}