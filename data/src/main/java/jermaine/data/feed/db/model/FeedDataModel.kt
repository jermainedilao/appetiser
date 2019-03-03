package jermaine.data.feed.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Data model representation of Feed. This is used in saving to DB.
 */
@Entity
data class FeedDataModel(
    @PrimaryKey
    val trackId: Long = 0,
    val artistName: String = "",
    val trackName: String = "",
    val artworkUrl100: String = "",
    val trackPrice: Double = 0.0,
    val primaryGenreName: String = "",
    val shortDescription: String = "",
    val longDescription: String = "",
    val trackTimeMillis: Long = 0
)