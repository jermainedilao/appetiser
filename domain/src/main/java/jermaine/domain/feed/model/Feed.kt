package jermaine.domain.feed.model


data class Feed(
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