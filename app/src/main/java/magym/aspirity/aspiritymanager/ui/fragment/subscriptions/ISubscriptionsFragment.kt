package magym.aspirity.aspiritymanager.ui.fragment.subscriptions

import android.support.design.widget.Snackbar

interface ISubscriptionsFragment {

    fun itemSelect(id: Int)

    fun openSubscription(idSubscription: String)

    fun showSnackbarWithButton(resId: Int, buttonTitle: String, funAction: () -> Unit): Snackbar

    fun setRefreshing(refreshing: Boolean)

    fun showSnackbar(resId: Int)

    fun hasConnection(): Boolean

    fun showException(t: Throwable)

}