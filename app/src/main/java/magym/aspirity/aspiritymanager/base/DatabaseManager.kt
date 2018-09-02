package magym.aspirity.aspiritymanager.base

import magym.aspirity.aspiritymanager.app.App
import magym.aspirity.aspiritymanager.model.Event
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template

class DatabaseManager {

    private val db = App.instance.database

    private val daoFilter by lazy { db.daoFilter() }

    fun insertEvent(event: Event) = daoEvent.insertElement(event)

    fun getAllEvents() = daoEvent.getAllEvents()

    fun getEvents(idSubscription: String) = daoEvent.getEvents(idSubscription)

    fun getEvent(idEvent: Long) = daoEvent.getEvent(idEvent)

    fun getCountEventsReaded(idSubscription: String) = daoEvent.getCountEventsReaded(idSubscription)

    fun getCountEvents(idSubscription: String) = daoEvent.getCountEvents(idSubscription)

    fun updateRead(idEvent: Long) = daoEvent.updateRead(idEvent)

    // -------------------------------------------------------------------------------------- //

    private val daoEvent by lazy { db.daoEvent() }

    fun updateFilters(filters: List<Filter>) {
        // Вставка в элемент списка дату добавления элемента
        val time = System.currentTimeMillis()
        for (i in filters.indices) {
            filters[i].dateAdd = time - i
        }

        // Получение старого списка
        val oldFilters = getAllFilters()

        // Удаление записей
        for (i in oldFilters.indices) {
            for (j in filters.indices) {
                if (oldFilters[i].idFilter == filters[j].idFilter) break
                else if (j == filters.size - 1) daoFilter.deleteFilter(oldFilters[i])
            }
        }

        // Обновление необходимых полей
        filters.forEach { daoFilter.updateFilter(it.idFilter, it.titleFilter, it.descriptionFilter) }

        // Вставка новых элементов
        daoFilter.insertFilters(filters)
    }

    private fun getAllFilters() = daoFilter.getAllFilters()

    fun getTitleFilter(idSubscription: String) = daoFilter.getTitle(idSubscription)

    // -------------------------------------------------------------------------------------- //

    private val daoTemplate by lazy { db.daoTemplate() }

    fun updateTemplates(templates: List<Template>) {
        val time = System.currentTimeMillis()
        for (i in templates.indices) {
            templates[i].dateAdd = time - i
        }

        val oldTemplates = getAllTemplates()

        for (i in oldTemplates.indices) {
            for (j in templates.indices) {
                if (oldTemplates[i].idTemplate == templates[j].idTemplate) break
                else if (j == templates.size - 1) daoTemplate.deleteTemplate(oldTemplates[i])
            }
        }

        templates.forEach { daoTemplate.updateTemplate(it.idTemplate, it.titleTemplate, it.descriptionTemplate) }

        daoTemplate.insertTemplates(templates)
    }

    private fun getAllTemplates() = daoTemplate.getAllTemplates()

    fun getEnable(idFilter: String, idTemplate: String) = daoTemplate.getEnable(idFilter, idTemplate)

    // -------------------------------------------------------------------------------------- //

    private val daoSubscription by lazy { db.daoSubscription() }

    fun updateSubscriptions(subscriptions: List<Subscription>) {
        val time = System.currentTimeMillis()
        for (i in subscriptions.indices) {
            subscriptions[i].dateAdd = time - i
        }

        val oldSubscriptions = getAllSubscriptions()

        for (i in oldSubscriptions.indices) {
            for (j in subscriptions.indices) {
                if (oldSubscriptions[i].idSubscription == subscriptions[j].idSubscription) break
                else if (j == subscriptions.size - 1) deleteSubscription(oldSubscriptions[i].idSubscription)
            }
        }

        subscriptions.forEach { daoSubscription.updateSubscription(it.idSubscription, it.idFilter, it.idTemplate) }

        daoSubscription.insertSubscriptions(subscriptions)
    }

    fun getAllSubscriptions() = daoSubscription.getAllSubscriptions()

    fun getSubscription(idSubscription: String) = daoSubscription.getSubscription(idSubscription)

    fun checkSubscription(idSubscription: String) = daoSubscription.checkSubscription(idSubscription)

    fun deleteSubscription(idSubscription: String) {
        daoEvent.deleteEvent(idSubscription)
        daoSubscription.deleteSubscription(idSubscription)
    }

    fun updateLastDateEvent(idSubscription: String, dateEvent: String) = daoSubscription.updateLastDateEvent(idSubscription, dateEvent)

}