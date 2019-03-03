package jermaine.appetiser.ui.home.adapter.callbacks

import jermaine.domain.feed.model.Feed


interface FeedClickListener {
    fun onClick(feed: Feed)
}