package magym.aspirity.aspiritymanager.base

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.request.RequestSubscription

@Dao
interface DaoSubscription {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubscriptions(subscriptions: List<Subscription>)

    @Query("UPDATE Subscription SET idFilter = :idFilter, idTemplate = :idTemplate WHERE idSubscription = :idSubscription")
    fun updateSubscription(idSubscription: String, idFilter: String, idTemplate: String)

    @Query("""SELECT Subscription.*,
        Filter.titleFilter, Filter.descriptionFilter,
        Template.titleTemplate, Template.descriptionTemplate
        FROM Subscription
        JOIN Filter ON Subscription.idFilter = Filter.idFilter
        JOIN Template ON Subscription.idTemplate = Template.idTemplate
        ORDER BY lastDateEvent DESC, dateAdd DESC""")
    fun getAllSubscriptions(): MutableList<RequestSubscription>

    @Query("""SELECT Subscription.*,
        Filter.titleFilter, Filter.descriptionFilter,
        Template.titleTemplate, Template.descriptionTemplate
        FROM Subscription
        JOIN Filter ON Subscription.idFilter = Filter.idFilter
        JOIN Template ON Subscription.idTemplate = Template.idTemplate
        WHERE idSubscription = :idSubscription""")
    fun getSubscription(idSubscription: String): RequestSubscription

    @Query("SELECT idSubscription FROM Subscription WHERE idSubscription = :idSubscription")
    fun checkSubscription(idSubscription: String): String

    @Query("DELETE FROM Subscription WHERE idSubscription = :idSubscription")
    fun deleteSubscription(idSubscription: String)

    @Query("UPDATE Subscription SET lastDateEvent = :dateEvent WHERE idSubscription = :idSubscription")
    fun updateLastDateEvent(idSubscription: String, dateEvent: String)

    @Query("SELECT COUNT(idEvent) FROM Event WHERE Event.idSubscription = :idSubscription")
    fun getCountSubscriptions(idSubscription: String): Int

}