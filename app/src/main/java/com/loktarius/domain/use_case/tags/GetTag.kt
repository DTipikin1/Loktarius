package com.loktarius.domain.use_case.tags

import com.loktarius.domain.model.Tag
import com.loktarius.domain.repository.ActivityRepository

class GetTag(
    val repository: ActivityRepository
) {

    suspend operator fun invoke(id: Int): Tag? {
        return repository.getTagById(id)
    }
}