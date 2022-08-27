package com.loktarius.feature_activity.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loktarius.feature_activity.domain.model.Tag

@Database(
    entities = [
        Tag::class
    ],
    version = 1
)
abstract class ActivityDatabase: RoomDatabase() {

    abstract val activityDao: ActivityDao

    companion object {
        val DATABASE_NAME = "activities_db"
    }
}