package magym.aspirity.aspiritymanager.ui.fragment.events

import magym.aspirity.aspiritymanager.model.request.RequestEvent

interface IEventView {

    fun openEvent(idEvent: Long)

    fun showSnackbar(resId: Int)

    fun setRefreshing(refreshing: Boolean)

    fun updateList(newList: MutableList<RequestEvent>)

    fun hasConnection(): Boolean

}