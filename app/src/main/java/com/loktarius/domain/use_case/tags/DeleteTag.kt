package com.loktarius.domain.use_case.tags

import com.loktarius.domain.model.Tag
import com.loktarius.domain.repository.ActivityRepository

class DeleteTag(
    private val repository: ActivityRepository
) {
    suspend operator fun invoke(tag: Tag) {
        repository.deleteTag(tag)
    }
}