package jermaine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jermaine.data.feed.db.model.FeedDataModelDao
import jermaine.data.feed.db.model.FeedDataModel


@Database(entities = [FeedDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDataModelDao
}