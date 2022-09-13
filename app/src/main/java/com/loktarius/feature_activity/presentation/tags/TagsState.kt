package com.loktarius.feature_activity.presentation.tags

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.util.OrderType
import com.loktarius.feature_activity.domain.util.TagOrder

data class TagsState(
    val tags: List<Tag> = emptyList(),
    val tagOrder: TagOrder = TagOrder.Date(OrderType.Descending),
    var lastUsedTag: Tag? = null
)
