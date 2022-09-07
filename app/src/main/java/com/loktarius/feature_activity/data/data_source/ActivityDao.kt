package com.loktarius.feature_activity.data.data_source

import androidx.room.*
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
    suspend fun getLastUsedTag(): Tag?
}