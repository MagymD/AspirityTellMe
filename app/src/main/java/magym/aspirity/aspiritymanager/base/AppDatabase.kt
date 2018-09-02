package magym.aspirity.aspiritymanager.base

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import magym.aspirity.aspiritymanager.model.Event
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template

@Database(entities = [(Event::class), (Filter::class), (Template::class), (Subscription::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoEvent(): DaoEvent

    abstract fun daoFilter(): DaoFilter

    abstract fun daoTemplate(): DaoTemplate

    abstract fun daoSubscription(): DaoSubscription

}