package jermaine.appetiser.ui.home.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jermaine.appetiser.R
import jermaine.appetiser.utils.GlideApp


@BindingAdapter("imageUrl", requireAll = false)
fun loadImage(view: ImageView, url: String?) {
    if (url == null || url.isEmpty()) return

    GlideApp.with(view.context)
        .load(url)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_error_black_24dp)
        .into(view)
}