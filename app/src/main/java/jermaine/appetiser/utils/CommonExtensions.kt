package jermaine.appetiser.utils

import android.content.Context
import android.net.ConnectivityManager


/**
 * Returns if device's wifi/mobile data is turned on.
 *
 * Note: This does not guarantee that it has internet connection.
 */
fun Context.hasNetworkConnection(): Boolean {
    val networkInfo = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}