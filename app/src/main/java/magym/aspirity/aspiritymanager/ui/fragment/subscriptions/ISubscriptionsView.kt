package magym.aspirity.aspiritymanager.ui.fragment.subscriptions

import android.support.design.widget.Snackbar
import magym.aspirity.aspiritymanager.model.request.RequestSubscription

interface ISubscriptionsView {

    fun itemSelect(id: Int)

    fun showSnackbar(resId: Int)

    fun showSnackbarWithButton(resId: Int, buttonTitle: String, funAction: () -> Unit): Snackbar

    fun setRefreshing(refreshing: Boolean)

    fun updateList(newList: List<RequestSubscription>)

    fun hasConnection(): Boolean

    fun showException(t: Throwable)

}