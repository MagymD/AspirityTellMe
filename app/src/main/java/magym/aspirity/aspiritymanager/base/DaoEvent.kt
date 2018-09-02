package magym.aspirity.aspiritymanager.base

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import magym.aspirity.aspiritymanager.model.Event
import magym.aspirity.aspiritymanager.model.request.RequestEvent

@Dao
interface DaoEvent {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertElement(event: Event)

    @Query("""SELECT Event.*, Filter.titleFilter, Template.titleTemplate FROM Event
        JOIN Subscription ON Subscription.idSubscription = Event.idSubscription
        JOIN Filter ON Filter.idFilter = Subscription.idFilter
        JOIN Template ON Template.idTemplate = Subscription.idTemplate
        ORDER BY dateEvent DESC""")
    fun getAllEvents(): MutableList<RequestEvent>

    @Query("""SELECT Event.*, Filter.titleFilter, Template.titleTemplate FROM Event
        JOIN Subscription ON Subscription.idSubscription = Event.idSubscription
        JOIN Filter ON Filter.idFilter = Subscription.idFilter
        JOIN Template ON Template.idTemplate = Subscription.idTemplate
        WHERE Event.idSubscription = :idSubscription
        ORDER BY dateEvent DESC""")
    fun getEvents(idSubscription: String): MutableList<RequestEvent>

    @Query("""SELECT Event.*, Filter.titleFilter, Template.titleTemplate FROM Event
        JOIN Subscription ON Subscription.idSubscription = Event.idSubscription
        JOIN Filter ON Filter.idFilter = Subscription.idFilter
        JOIN Template ON Template.idTemplate = Subscription.idTemplate
        WHERE Event.idEvent = :idEvent""")
    fun getEvent(idEvent: Long): RequestEvent

    @Query("SELECT COUNT(idEvent) FROM Event WHERE Event.idSubscription = :idSubscription AND readed = 0")
    fun getCountEventsReaded(idSubscription: String): Int

    @Query("SELECT COUNT(idEvent) FROM Event WHERE Event.idSubscription = :idSubscription")
    fun getCountEvents(idSubscription: String): Int

    @Query("UPDATE Event SET readed = 1 WHERE idEvent = :idEvent")
    fun updateRead(idEvent: Long)

    @Query("DELETE FROM Event WHERE idSubscription = :idSubscription")
    fun deleteEvent(idSubscription: String)

}