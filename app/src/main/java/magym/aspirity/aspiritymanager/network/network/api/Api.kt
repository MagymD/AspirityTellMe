package magym.aspirity.aspiritymanager.network.network.api

import io.reactivex.Single
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.model.other.SubscriptionPost
import retrofit2.http.*

interface Api {

    @GET("api/filters")
    //@GET("7de513ae-5322-49da-9abc-fe775830f96c")
    fun getFilters(): Single<List<Filter>>

    @GET("api/templates")
    //@GET("429b58fa-1d6d-4057-98b9-94d6bbb68165")
    fun getTemplates(): Single<List<Template>>

    @GET("api/subscriptions")
    //@GET("a0e830b1-c639-4a0a-a53f-0931da9543fc")
    fun getSubscriptions(@Query("regToken") idDevice: String): Single<List<Subscription>>

    @POST("api/subscriptions")
    //@POST("a0e830b1-c639-4a0a-a53f-0931da9543fc")
    @Headers("Content-Type: application/json")
    fun postSubscription(@Body subscription: SubscriptionPost): Single<String>

    @DELETE("api/subscriptions/{id}")
    //@DELETE("a0e830b1-c639-4a0a-a53f-0931da9543fc/{id}")
    fun deleteSubscription(@Path("id") id: String): Single<String>

}