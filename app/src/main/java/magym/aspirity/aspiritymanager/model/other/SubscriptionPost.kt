package magym.aspirity.aspiritymanager.model.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubscriptionPost {

    @SerializedName("regToken")
    @Expose
    var idDevice: String = ""

    @SerializedName("filterId")
    @Expose
    var idFilter: String = ""

    @SerializedName("templateId")
    @Expose
    var idTemplate: String = ""

    @SerializedName("channel")
    @Expose
    val channel: String = "android"

}