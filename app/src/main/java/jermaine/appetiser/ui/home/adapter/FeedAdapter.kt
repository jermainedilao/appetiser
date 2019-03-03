package jermaine.appetiser.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jermaine.appetiser.R
import jermaine.appetiser.databinding.ItemFeedBinding
import jermaine.appetiser.ui.home.adapter.callbacks.FeedClickListener
import jermaine.appetiser.utils.HEADER
import jermaine.appetiser.utils.VIEW_TYPE_FEED
import jermaine.appetiser.utils.VIEW_TYPE_HEADER
import jermaine.domain.feed.model.Feed


class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var feedList: MutableList<Feed> = mutableListOf()
    private var clickListener: FeedClickListener? = null

    fun setClickListener(listener: FeedClickListener) {
        clickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return if (feedList[position].artistName == HEADER) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_FEED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(
                inflater.inflate(
                    R.layout.item_header, parent, false
                )
            )
        } else {
            FeedViewHolder(
                ItemFeedBinding.inflate(
                    inflater, parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = feedList[position]
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            (holder as HeaderViewHolder).bind(item)
        } else {
            (holder as FeedViewHolder).bind(item, View.OnClickListener { clickListener?.onClick(item) })
        }
    }

    fun updateData(feedList: List<Feed>) {
        this.feedList = feedList.toMutableList()
        notifyDataSetChanged()
    }
}