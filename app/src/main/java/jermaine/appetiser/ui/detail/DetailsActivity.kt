package jermaine.appetiser.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import jermaine.appetiser.R
import jermaine.appetiser.databinding.ActivityDetailsBinding
import jermaine.appetiser.utils.EXTRA_FEED_JSON
import jermaine.domain.feed.model.Feed


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val feed = Gson().fromJson(
            intent?.extras?.getString(EXTRA_FEED_JSON, "{}"),
            Feed::class.java
        )

        (DataBindingUtil.setContentView(this, R.layout.activity_details) as ActivityDetailsBinding)
            .apply {
                this.feed = feed
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}