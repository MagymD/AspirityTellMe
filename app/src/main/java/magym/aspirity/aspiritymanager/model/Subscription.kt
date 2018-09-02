package magym.aspirity.aspiritymanager.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Filter + Template
@Entity
class Subscription {

    @PrimaryKey()
    @SerializedName("_id")
    @Expose
    var idSubscription: String = ""

    @SerializedName("filterId")
    @Expose
    var idFilter: String = ""

    @SerializedName("templateId")
    @Expose
    var idTemplate: String = ""

    var dateAdd: Long = 0

    var lastDateEvent: String = "0"

}