package magym.aspirity.aspiritymanager.ui.fragment.events

import magym.aspirity.aspiritymanager.base.DatabaseManager

class EventsModel {

    private val databaseManager = DatabaseManager()

    internal fun getAllEvents() = databaseManager.getAllEvents()

    internal fun getEvents(idSubscription: String) = databaseManager.getEvents(idSubscription)

}