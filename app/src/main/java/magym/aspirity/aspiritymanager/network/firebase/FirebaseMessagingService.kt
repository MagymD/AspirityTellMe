package magym.aspirity.aspiritymanager.network.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.Event
import magym.aspirity.aspiritymanager.ui.activity.main.MainActivity
import magym.aspirity.aspiritymanager.ui.activity.subscription.SubscriptionActivity
import magym.aspirity.aspiritymanager.utils.fromHtml

class FirebaseMessagingService : FirebaseMessagingService() {

    private val firebaseModel = FirebaseModel()

    // fixme Работает не на всех устройствах
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data

        // Получаем данные уведомление:

        val idSubscription = data["idSubscription"].toString()

        val titleFilter = firebaseModel.getTitleFilter(idSubscription)

        var descriptionEvent = data["descriptionEvent"].toString()
        if (descriptionEvent == "" || descriptionEvent == "null") descriptionEvent = getString(R.string.text_of_the_notification_is_missing)

        var dateEvent = data["dateEvent"].toString()
        if (dateEvent == "" || dateEvent == "null") dateEvent = System.currentTimeMillis().toString()

        // Проверяем наличие данной пописки
        val idSubscriptionData = async { firebaseModel.checkSubscription(idSubscription) }

        launch(UI) {
            val checkIdSubscription: String? = idSubscriptionData.await()

            Log.d("qwerty", getString(R.string.a_new_massage_is_received))

            if (checkIdSubscription != null) {
                Log.d("qwerty", getString(R.string.by_subscription) + idSubscription)

                // Формируем уведомление
                val event = Event()
                event.idSubscription = idSubscription
                event.descriptionEvent = descriptionEvent
                event.dateEvent = dateEvent

                // Записываем уведомление в БД
                val wait = launch {
                    firebaseModel.insertEvent(event)
                    firebaseModel.updateLastDateEvent(idSubscription, dateEvent)
                }

                // Обновляем view
                launch(UI) {
                    wait.join()
                    MainActivity.requestData(true)
                    SubscriptionActivity.requestData(idSubscription)
                }

                // Отсылаем уведомление
                sendNotification(titleFilter, descriptionEvent.fromHtml().toString())
            }
        }
    }

    private fun sendNotification(title: String = "", body: String = "") {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_notification)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    getString(R.string.notifications),
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

}

/*

Request URL: https://fcm.googleapis.com/fcm/send

Authorization : key=KEY
Content-Type : application/json

{
  "to": "dgV8yFz7m9E:APA91bGmybsZ5JY7A9hKheakECbfMg92ozIdQtU1tEF4PcBsvCc-HYQbRqU616BumH9RvDRZnJIebf1p4_yaNJNZoIyadXZrjTcVM36cQOKGaJzNyCJGXL-WuTrv6yyjH1i5IV_ydIH5",
  "data": {
  		"idSubscription": "5b7f87c879ba6a18549ec82e",
  		"descriptionEvent": "qwerty"
  }
}

*/