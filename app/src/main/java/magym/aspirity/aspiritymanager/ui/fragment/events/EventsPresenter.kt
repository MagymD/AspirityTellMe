package magym.aspirity.aspiritymanager.ui.fragment.events

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.request.RequestEvent

class EventsPresenter(private val view: IEventView) {

    private val model = EventsModel()

    internal fun requestData(idSubscription: String = "") {
        view.hasConnection()
        readDb(idSubscription)
    }

    internal fun readDb(idSubscription: String = "") {
        val data = async {
            if (idSubscription == "") model.getAllEvents()
            else model.getEvents(idSubscription)
        }

        launch(UI) {
            val events = data.await()
            view.updateList(events)
            checkForPresents(events)
            view.setRefreshing(false)
        }
    }


    private fun checkForPresents(events: List<RequestEvent>) {
        if (events.isEmpty()) view.showSnackbar(R.string.the_notifications_list_is_empty)
    }

}