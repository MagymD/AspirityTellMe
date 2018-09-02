package magym.aspirity.aspiritymanager.ui.activity.event

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class EventPresenter(private val view: IEventView) {

    private val model = EventModel()

    internal fun initEvent(idEvent: Long) {
        val data = async { model.getEvent(idEvent) }
        launch(UI) { view.initEvent(data.await()) }
    }

    internal fun updateRead(idEvent: Long) = launch { model.updateRead(idEvent) }

}