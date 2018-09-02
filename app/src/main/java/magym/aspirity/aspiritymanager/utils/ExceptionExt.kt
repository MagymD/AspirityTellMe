package magym.aspirity.aspiritymanager.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import magym.aspirity.aspiritymanager.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLProtocolException

fun Activity.showExceptionExt(view: View, t: Throwable? = null): Boolean {
    Log.e("qwerty", "", t)

    if (!hasConnection(view)) return false

    when (t) {
        null -> return true
        is IllegalArgumentException, is HttpException, is ConnectException,
        is SSLHandshakeException, is SSLProtocolException, is RuntimeException,
        is UnknownHostException -> view.showSnackbarExt(getString(R.string.error_the_server_is_not_available))
        is SocketTimeoutException -> view.showSnackbarExt(getString(R.string.error_connection_timed_out))
        else -> view.showSnackbarExt(getString(R.string.error_a_network_error_occurred))
    }
    return true
}

private fun Activity.hasConnection(view: View): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return when (cm.activeNetworkInfo) {
        null -> {
            view.showSnackbarExt(getString(R.string.error_no_internet_connection))
            false
        }
        else -> true
    }
}