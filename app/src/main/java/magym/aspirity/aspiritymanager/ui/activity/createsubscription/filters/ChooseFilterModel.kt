package magym.aspirity.aspiritymanager.ui.activity.createsubscription.filters

import magym.aspirity.aspiritymanager.base.DatabaseManager
import magym.aspirity.aspiritymanager.model.Filter

class ChooseFilterModel {

    private val databaseManager = DatabaseManager()

    internal fun updateFilters(filters: List<Filter>) = databaseManager.updateFilters(filters)

}