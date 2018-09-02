package magym.aspirity.aspiritymanager.ui.activity.createsubscription.templates

import magym.aspirity.aspiritymanager.base.DatabaseManager
import magym.aspirity.aspiritymanager.model.Template

class ChooseTemplateModel {

    private val databaseManager = DatabaseManager()

    internal fun updateTemplates(templates: List<Template>) = databaseManager.updateTemplates(templates)

    internal fun getEnable(idFilter: String, idTemplate: String) = databaseManager.getEnable(idFilter, idTemplate)

}