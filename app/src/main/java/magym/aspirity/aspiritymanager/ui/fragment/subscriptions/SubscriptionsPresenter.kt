package magym.aspirity.aspiritymanager.ui.fragment.subscriptions

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.network.network.DataRequestManager

class SubscriptionsPresenter(private val view: ISubscriptionsView) {

    private val requestData = DataRequestManager()
    private val model = SubscriptionsModel()

    internal fun requestDara(push: Boolean) {
        if (push) {
            readDb(true)
        } else {
            view.setRefreshing(true)

            if (!view.hasConnection()) {
                readDb(false)
                view.setRefreshing(false)
                return
            }

            val job = launch {
                requestData.requestGetFilters({
                    model.updateFilters(it)
                }, { })

                requestData.requestGetTemplates({
                    model.updateTemplates(it)
                }, { })
            }

            val subscriptionJob = launch {
                job.join()

                requestData.requestGetSubscription({
                    if (it.isEmpty()) view.showSnackbar(R.string.the_subscriptions_list_is_empty)
                    model.updateSubscriptions(it)
                }, { view.showException(it) })
            }

            launch(UI) {
                subscriptionJob.join()
                readDb(true)
                view.setRefreshing(false)
            }
        }
    }

    internal fun readDb(checkMyFiltersOnNull: Boolean) {
        val data = async {
            model.getAllSubscriptions()
        }

        launch(UI) {
            val subscriptions = data.await()

            if (subscriptions.isEmpty() && checkMyFiltersOnNull) return@launch

            val job = launch { subscriptions.forEach { it.countEvents = model.getCountEventsReaded(it.idSubscription) } }

            launch(UI) {
                job.join()
                view.updateList(subscriptions)
            }
        }
    }

}