package magym.aspirity.aspiritymanager.utils

import android.app.Activity
import android.content.Intent
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.consts.ViewEn
import magym.aspirity.aspiritymanager.ui.activity.event.EventActivity
import magym.aspirity.aspiritymanager.ui.activity.main.MainActivity
import magym.aspirity.aspiritymanager.ui.activity.subscription.SubscriptionActivity

fun Activity.openSubscriptionExt(idSubscription: String) {
    val intent = Intent(this, SubscriptionActivity::class.java)
    intent.putExtra(ModelEn.ID_SUBSCRIPTION.const, idSubscription)
    startActivity(intent)
}

fun Activity.openEventExt(idEvent: Long) {
    val intent = Intent(this, EventActivity::class.java)
    intent.putExtra(ModelEn.ID_EVENT.const, idEvent)
    startActivity(intent)
}

fun Activity.openMainActivityExt() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(ViewEn.NEW_SUBSCRIPTION.const, 1)
    startActivity(intent)
}