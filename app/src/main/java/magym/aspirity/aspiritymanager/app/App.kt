package magym.aspirity.aspiritymanager.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.arch.persistence.room.Room
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.base.AppDatabase

class App : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration() // Attention
                .build()
    }

    companion object {

        lateinit var instance: App

        private val sLock = Any()

    }

    override fun onCreate() {
        super.onCreate()
        synchronized(sLock) { instance = this }

        createChannelNotification()

        // Test
        subscribeToTopic("news") // /topics/news

        //Stetho()
    }


    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW))
        }
    }

    private fun subscribeToTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener { task -> if (task.isSuccessful) Log.v("qwerty", "Topic added: $topic") }
    }

    private fun Stetho() {
        // Create an InitializerBuilder
        val initializerBuilder = com.facebook.stetho.Stetho.newInitializerBuilder(this)

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(com.facebook.stetho.Stetho.defaultInspectorModulesProvider(this))

        // Enable command line interface
        initializerBuilder.enableDumpapp(com.facebook.stetho.Stetho.defaultDumperPluginsProvider(this))

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer
        com.facebook.stetho.Stetho.initialize(initializer)
    }

}