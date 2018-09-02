package magym.aspirity.aspiritymanager.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Filter {

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    var idFilter: String = ""

    @SerializedName("name")
    @Expose
    var titleFilter: String = ""

    @SerializedName("description")
    @Expose
    var descriptionFilter: String = ""

    var dateAdd: Long = 0

}