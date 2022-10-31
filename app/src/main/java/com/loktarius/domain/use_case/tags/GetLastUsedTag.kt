package com.loktarius.domain.use_case.tags

import com.loktarius.domain.model.Tag
import com.loktarius.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetLastUsedTag(
    val repository: ActivityRepository
) {

     operator fun invoke(): Flow<Tag?> {
        return repository.getLastUsedTag()
    }
}