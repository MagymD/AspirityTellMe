package magym.aspirity.aspiritymanager.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Template {

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    var idTemplate: String = ""

    @SerializedName("name")
    @Expose
    var titleTemplate: String = ""

    @SerializedName("description")
    @Expose
    var descriptionTemplate: String = ""

    var dateAdd: Long = 0

    @Ignore
    var enable: String = ""

}