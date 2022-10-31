package com.loktarius.domain.use_case.tags

import com.loktarius.domain.model.InvalidTagException
import com.loktarius.domain.model.Tag
import com.loktarius.domain.repository.ActivityRepository

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