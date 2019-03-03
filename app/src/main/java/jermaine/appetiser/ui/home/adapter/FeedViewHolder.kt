package jermaine.appetiser.ui.home.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jermaine.appetiser.BR
import jermaine.domain.feed.model.Feed


class FeedViewHolder(
    private val dataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(feed: Feed, clickListener: View.OnClickListener) {
        dataBinding.setVariable(BR.feed, feed)
        dataBinding.setVariable(BR.clickListener, clickListener)
    }
}