package magym.aspirity.aspiritymanager.ui.activity.subscription

import magym.aspirity.aspiritymanager.base.DatabaseManager

class SubscriptionModel {

    private val databaseManager = DatabaseManager()

    internal fun getSubscription(idSubscription: String) = databaseManager.getSubscription(idSubscription)

    internal fun deleteSubscription(idSubscription: String) = databaseManager.deleteSubscription(idSubscription)

    internal fun getCountEvents(idSubscription: String) = databaseManager.getCountEvents(idSubscription)

}