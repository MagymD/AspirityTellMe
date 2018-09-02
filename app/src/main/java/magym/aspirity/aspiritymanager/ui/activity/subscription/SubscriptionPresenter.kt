package magym.aspirity.aspiritymanager.ui.activity.subscription

import android.app.FragmentManager
import android.os.Bundle
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.network.network.DataRequestManager
import magym.aspirity.aspiritymanager.ui.fragment.events.EventsFragment

class SubscriptionPresenter(private val view: ISubscriptionView,
                            private val fragmentManager: FragmentManager) {

    private val requestData = DataRequestManager()
    private val model = SubscriptionModel()

    private val eventsFragment = EventsFragment()

    internal fun initSubscription(idSubscription: String) {
        val subscription = async { model.getSubscription(idSubscription) }
        val countSubscriptions = async { model.getCountEvents(idSubscription) }
        launch(UI) { view.initSubscription(subscription.await(), countSubscriptions.await()) }
    }

    internal fun addEventFragment(idSubscription: String) {
        val bundle = Bundle()
        bundle.putString(ModelEn.ID_SUBSCRIPTION.const, idSubscription)
        eventsFragment.arguments = bundle

        fragmentManager.beginTransaction()
                .add(R.id.filter_fragment_container, eventsFragment)
                .commit()
    }

    internal fun deleteSubscription(idSubscription: String) {
        if (!view.hasConnection()) return

        launch {
            requestData.requestDeleteSubscription({
                val job = launch { model.deleteSubscription(idSubscription) }

                launch(UI) {
                    job.join()
                    view.openMainActivity()
                }
            }, { view.showSnackbar(R.string.error_unsubscribe_failed) }, idSubscription)
        }
    }

    internal fun requestData(idSubscription: String) = eventsFragment.requestData(idSubscription)

}