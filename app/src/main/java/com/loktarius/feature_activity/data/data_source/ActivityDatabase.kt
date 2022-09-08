package com.loktarius.feature_activity.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.model.Tag

@Database(
    entities = [
        Activity::class,
        Tag::class
    ],
    version = 2
)
abstract class ActivityDatabase: RoomDatabase() {

    abstract val activityDao: ActivityDao

    companion object {
        val DATABASE_NAME = "activities_db"
    }
}