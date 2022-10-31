package com.loktarius.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag

@Database(
    entities = [
        Activity::class,
        Tag::class
    ],
    version = 7
)
abstract class ActivityDatabase: RoomDatabase() {

    abstract val activityDao: ActivityDao

    companion object {
        val DATABASE_NAME = "activities_db"
    }
}