package com.loktarius.feature_activity.domain.use_case.tags

import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.repository.ActivityRepository
import com.loktarius.feature_activity.domain.util.OrderType
import com.loktarius.feature_activity.domain.util.TagOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTags(
    private val repository: ActivityRepository
) {

    operator fun invoke(
        tagOrder: TagOrder = TagOrder.Date(OrderType.Descending)
    ): Flow<List<Tag>> {
        return repository.getTags().map { tags ->
            when(tagOrder.orderType) {
                is OrderType.Ascending -> {
                    when(tagOrder) {
                        is TagOrder.Name -> tags.sortedBy { it.name.lowercase()}
                        is TagOrder.Date -> tags.sortedBy { it.timestamp }

                    }

                }
                is OrderType.Descending -> {
                    when(tagOrder) {
                        is TagOrder.Name -> tags.sortedByDescending { it.name.lowercase()}
                        is TagOrder.Date -> tags.sortedByDescending { it.timestamp }

                    }

                }
            }
        }
    }
}