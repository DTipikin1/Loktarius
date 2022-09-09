package com.loktarius.feature_activity.data.data_source

import androidx.room.*
import com.loktarius.feature_activity.domain.model.Activity
import com.loktarius.feature_activity.domain.model.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM tag")
    fun getTags(): Flow<List<Tag>>

    @Query("SELECT * FROM tag WHERE id = :id")
    suspend fun getTagById(id: Int): Tag?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)

    @Query("SELECT * FROM tag ORDER BY timestamp DESC LIMIT 1")
    fun getLastUsedTag(): Flow<Tag?>

    @Query("SELECT * FROM activity")
    fun getActivities(): Flow<List<Activity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity)

    @Delete
    suspend fun deleteActivity(activity: Activity)
}