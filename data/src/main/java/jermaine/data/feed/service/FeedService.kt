package jermaine.data.feed.service

import io.reactivex.Observable
import jermaine.domain.feed.model.Feed


interface FeedService {
    fun fetchFeed(): Observable<List<Feed>>
}