package com.loktarius.feature_activity.domain.use_case

data class TagUseCases(
    val getTags: GetTags,
    val deleteTag: DeleteTag,
    val addTag: AddTag
)
