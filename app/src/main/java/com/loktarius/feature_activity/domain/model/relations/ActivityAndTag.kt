package com.loktarius.feature_activity.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.model.Tag


data class ActivityAndTag(
    @Embedded val activity: Activity,
    @Relation(
        parentColumn ="tagId",
        entityColumn = "id"
    )
    val tag: Tag
)