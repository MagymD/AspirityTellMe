package magym.aspirity.aspiritymanager.ui.fragment.subscriptions

import magym.aspirity.aspiritymanager.base.DatabaseManager
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template

class SubscriptionsModel {

    private val databaseManager = DatabaseManager()

    internal fun getAllSubscriptions() = databaseManager.getAllSubscriptions()

    internal fun getCountEventsReaded(idSubscription: String) = databaseManager.getCountEventsReaded(idSubscription)

    internal fun updateFilters(filters: List<Filter>) = databaseManager.updateFilters(filters)

    internal fun updateTemplates(templates: List<Template>) = databaseManager.updateTemplates(templates)

    internal fun updateSubscriptions(subscription: List<Subscription>) = databaseManager.updateSubscriptions(subscription)

}