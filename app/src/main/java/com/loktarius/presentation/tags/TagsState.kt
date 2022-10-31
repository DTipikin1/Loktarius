package com.loktarius.presentation.tags

import com.loktarius.domain.model.Tag
import com.loktarius.domain.util.OrderType
import com.loktarius.domain.util.TagOrder

data class TagsState(
    val tags: List<Tag> = emptyList(),
    val tagOrder: TagOrder = TagOrder.Date(OrderType.Descending),
    var lastUsedTag: Tag? = null
)
