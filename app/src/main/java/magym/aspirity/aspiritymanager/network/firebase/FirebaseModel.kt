package magym.aspirity.aspiritymanager.network.firebase

import magym.aspirity.aspiritymanager.base.DatabaseManager
import magym.aspirity.aspiritymanager.model.Event

class FirebaseModel {

    private val databaseManager = DatabaseManager()

    internal fun insertEvent(event: Event) = databaseManager.insertEvent(event)

    internal fun getTitleFilter(idSubscription: String) = databaseManager.getTitleFilter(idSubscription)

    internal fun updateLastDateEvent(idSubscription: String, dateEvent: String) = databaseManager.updateLastDateEvent(idSubscription, dateEvent)

    internal fun checkSubscription(idSubscription: String) = databaseManager.checkSubscription(idSubscription)

}