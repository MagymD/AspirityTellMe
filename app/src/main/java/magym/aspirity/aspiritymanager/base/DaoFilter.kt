package magym.aspirity.aspiritymanager.base

import android.arch.persistence.room.*
import magym.aspirity.aspiritymanager.model.Filter

@Dao
interface DaoFilter {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFilters(filters: List<Filter>)

    @Query("UPDATE Filter SET titleFilter = :titleFilter, descriptionFilter = :descriptionFilter WHERE idFilter = :idFilter")
    fun updateFilter(idFilter: String, titleFilter: String, descriptionFilter: String)

    @Delete
    fun deleteFilter(filter: Filter)

    @Query("SELECT * FROM Filter ORDER BY dateAdd DESC")
    fun getAllFilters(): MutableList<Filter>

    @Query("SELECT Filter.titleFilter FROM Filter JOIN Subscription ON Subscription.idFilter = Filter.idFilter WHERE Subscription.idSubscription = :idSubscription")
    fun getTitle(idSubscription: String): String

}