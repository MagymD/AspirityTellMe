package magym.aspirity.aspiritymanager.network.network.api

import io.reactivex.Single
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.model.other.SubscriptionPost
import retrofit2.http.*

interface Api {

    //@GET("api/filters")
    @GET("72a4dc6a-cf7f-4934-bd47-36a33a2f5e8e")
    fun getFilters(): Single<List<Filter>>

    //@GET("api/templates")
    @GET("197b6d7c-2b3d-4000-84be-6d7c9340574b")
    fun getTemplates(): Single<List<Template>>

    //@GET("api/subscriptions")
    @GET("b65f7f7e-5a88-4824-bf67-04a506dd3e5b")
    fun getSubscriptions(@Query("regToken") idDevice: String): Single<List<Subscription>>

    //@POST("api/subscriptions")
    @POST("b65f7f7e-5a88-4824-bf67-04a506dd3e5b")
    @Headers("Content-Type: application/json")
    fun postSubscription(@Body subscription: SubscriptionPost): Single<String>

    //@DELETE("api/subscriptions/{id}")
    @DELETE("b65f7f7e-5a88-4824-bf67-04a506dd3e5b/{id}")
    fun deleteSubscription(@Path("id") id: String): Single<String>

}