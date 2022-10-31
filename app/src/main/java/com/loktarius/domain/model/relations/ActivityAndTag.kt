package com.loktarius.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag


data class ActivityAndTag(
    @Embedded val activity: Activity,
    @Relation(
        parentColumn ="tagId",
        entityColumn = "id"
    )
    val tag: Tag
)