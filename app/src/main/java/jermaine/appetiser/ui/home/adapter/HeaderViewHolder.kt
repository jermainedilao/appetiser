package jermaine.appetiser.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jermaine.domain.feed.model.Feed
import kotlinx.android.synthetic.main.item_header.view.*
import java.text.SimpleDateFormat
import java.util.*


class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(feed: Feed) {
        val date = SimpleDateFormat("EEE, MMM dd, yyyy hh:mm aa", Locale.US)
        itemView.textViewHeader.text = "Your last visit was on ${date.format(Date(feed.trackId))}"
    }
}