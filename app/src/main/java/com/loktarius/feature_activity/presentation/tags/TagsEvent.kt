package com.loktarius.feature_activity.presentation.tags

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.util.TagOrder

sealed class TagsEvent {
    data class Order(val tagOrder: TagOrder): TagsEvent()
    data class DeleteTag(val tag: Tag): TagsEvent()
    data class ChooseTag(val tag: Tag): TagsEvent()
    object RestoreTag: TagsEvent()
}
