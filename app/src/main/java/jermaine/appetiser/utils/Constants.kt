package jermaine.appetiser.utils


const val HEADER = "VIEW_TYPE_HEADER"
const val VIEW_TYPE_HEADER = 0
const val VIEW_TYPE_FEED = 1

const val EXTRA_FEED_JSON = "EXTRA_FEED_JSON"

const val PREF_LAST_FETCH_TIMESTAMP = "PREF_LAST_FETCH_TIMESTAMP"
const val PREF_LAST_VISIT = "PREF_LAST_VISIT"

/**
 * Interval for fetching feed from server to avoid getting blocked/banned.
 */
const val PREF_FETCH_INTERVAL = 300000  // 5 minutes