package magym.aspirity.aspiritymanager.ui.fragment.events

interface IEventFragment {

    fun openEvent(idEvent: Long)

    fun setRefreshing(refreshing: Boolean)

    fun showSnackbar(resId: Int)

    fun hasConnection(): Boolean

    fun showException(t: Throwable)

}