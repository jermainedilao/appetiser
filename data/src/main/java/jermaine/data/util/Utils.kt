package jermaine.data.util

import jermaine.data.feed.db.model.FeedDataModel
import jermaine.domain.feed.model.Feed


/**
 * Converts `domain.feed.model.Feed` to FeedDataModel.
 *
 * @see Feed
 */
fun Feed.toDataModel(): FeedDataModel = FeedDataModel(
    trackId,
    artistName,
    trackName,
    artworkUrl100,
    trackPrice,
    primaryGenreName,
    shortDescription,
    longDescription,
    trackTimeMillis
)

/**
 * Converts FeedDataModel to `domain.feed.model.Feed`.
 *
 * @see Feed
 */
fun FeedDataModel.toDomainModel(): Feed = Feed(
    trackId,
    artistName,
    trackName,
    artworkUrl100,
    trackPrice,
    primaryGenreName,
    shortDescription,
    longDescription,
    trackTimeMillis
)