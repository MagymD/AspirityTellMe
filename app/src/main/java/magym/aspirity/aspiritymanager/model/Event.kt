package magym.aspirity.aspiritymanager.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Event {

    @PrimaryKey(autoGenerate = true)
    var idEvent: Long = 0

    var idSubscription: String = ""

    var descriptionEvent: String = ""

    var dateEvent: String = "0"

    var readed: Int = 0

}