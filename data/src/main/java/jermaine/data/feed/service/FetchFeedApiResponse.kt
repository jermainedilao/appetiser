package jermaine.data.feed.service

import jermaine.domain.feed.model.Feed


data class FetchFeedApiResponse(
    val results: List<Feed>
)