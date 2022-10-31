package com.loktarius.domain.use_case.tags


data class TagUseCases(
    val getTags: GetTags,
    val deleteTag: DeleteTag,
    val addTag: AddTag,
    val getTag: GetTag,
    val getLastUsedTag: GetLastUsedTag
)
