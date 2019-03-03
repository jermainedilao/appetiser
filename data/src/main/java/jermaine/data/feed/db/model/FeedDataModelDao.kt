package jermaine.data.feed.db.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single


@Dao
interface FeedDataModelDao {
    @Query("SELECT * FROM FeedDataModel")
    fun fetchFeedList(): Single<List<FeedDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFeedList(value: List<FeedDataModel>)
}