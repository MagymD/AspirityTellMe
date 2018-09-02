package magym.aspirity.aspiritymanager.base

import android.arch.persistence.room.*
import magym.aspirity.aspiritymanager.model.Template

@Dao
interface DaoTemplate {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTemplates(templates: List<Template>)

    @Query("UPDATE Template SET titleTemplate = :titleTemplate, descriptionTemplate = :descriptionTemplate WHERE idTemplate = :idTemplate")
    fun updateTemplate(idTemplate: String, titleTemplate: String, descriptionTemplate: String)

    @Query("""SELECT idTemplate FROM Subscription
        WHERE Subscription.idFilter = :idFilter AND Subscription.idTemplate = :idTemplate""")
    fun getEnable(idFilter: String, idTemplate: String): String

    @Delete
    fun deleteTemplate(template: Template)

    @Query("SELECT * FROM Template ORDER BY dateAdd DESC")
    fun getAllTemplates(): MutableList<Template>

}