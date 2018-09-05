package magym.aspirity.aspiritymanager.network.network

import com.google.gson.GsonBuilder
import magym.aspirity.aspiritymanager.network.network.api.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object
NetworkManager {

    val api by lazy { create() }

    private fun create(): Api {
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

        val gson = GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.baseUrl("http://notterox.ru:4000/")
                .baseUrl("https://webhook.site/")
                .build()
                .create(Api::class.java)
    }

}