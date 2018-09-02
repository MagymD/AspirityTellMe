package magym.aspirity.aspiritymanager.model.request

import android.arch.persistence.room.Ignore

class RequestSubscription {

    var idSubscription: String = ""
    var idFilter: String = ""
    var idTemplate: String = ""
    var lastDateEvent: String = "0"

    var titleFilter: String = ""
    var descriptionFilter: String = ""

    var titleTemplate: String = ""
    var descriptionTemplate: String = ""

    var dateAdd: Long = 0

    @Ignore
    var countEvents: Int = 0

}