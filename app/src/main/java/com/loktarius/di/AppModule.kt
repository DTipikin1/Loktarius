package com.loktarius.di

import android.app.Application
import androidx.room.Room
import com.loktarius.feature_activity.data.data_source.ActivityDatabase
import com.loktarius.feature_activity.data.repository.ActivityRepositoryImpl
import com.loktarius.feature_activity.domain.repository.ActivityRepository
import com.loktarius.feature_activity.domain.use_case.TagUseCases
import com.loktarius.feature_activity.domain.use_case.deleteTag
import com.loktarius.feature_activity.domain.use_case.getTags
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideActivityDatabase(app: Application): ActivityDatabase {
        return Room.databaseBuilder(
            app,
            ActivityDatabase::class.java,
            ActivityDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideActivityRepository(db: ActivityDatabase): ActivityRepository {
        return ActivityRepositoryImpl(db.activityDao)
    }

    @Provides
    @Singleton
    fun provideTagsUseCases(repository: ActivityRepository): TagUseCases {
        return TagUseCases(
            getTags = getTags(repository),
            deleteTag = deleteTag(repository)
        )
    }
}