package jermaine.data

import io.reactivex.Observable
import jermaine.data.feed.service.FetchFeedApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/search")
    fun fetchFeed(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Observable<FetchFeedApiResponse>
}