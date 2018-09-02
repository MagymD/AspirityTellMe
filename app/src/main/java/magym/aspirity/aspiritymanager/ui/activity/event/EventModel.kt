package magym.aspirity.aspiritymanager.ui.activity.event

import magym.aspirity.aspiritymanager.base.DatabaseManager

class EventModel {

    private val databaseManager = DatabaseManager()

    internal fun getEvent(idEvent: Long) = databaseManager.getEvent(idEvent)

    internal fun updateRead(idEvent: Long) = databaseManager.updateRead(idEvent)

}