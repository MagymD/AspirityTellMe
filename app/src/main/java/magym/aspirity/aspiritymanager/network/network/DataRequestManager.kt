package magym.aspirity.aspiritymanager.network.network

import com.google.firebase.iid.FirebaseInstanceId
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.model.other.SubscriptionPost

class DataRequestManager {

    private val manager = NetworkRequestManager()

    fun requestGetFilters(callback: (List<Filter>) -> Unit, fallback: (Throwable) -> Unit) {
        val data = manager.getFiltersData()

        return when (data) {
            is RequestResult.Result -> callback(data.data)
            is RequestResult.Error -> fallback(data.error)
        }
    }

    fun requestGetTemplates(callback: (List<Template>) -> Unit, fallback: (Throwable) -> Unit) {
        val data = manager.getTemplatesData()

        return when (data) {
            is RequestResult.Result -> callback(data.data)
            is RequestResult.Error -> fallback(data.error)
        }
    }

    fun requestGetSubscription(callback: (List<Subscription>) -> Unit, fallback: (Throwable) -> Unit) {
        val token = FirebaseInstanceId.getInstance().token
        token?.let {
            val data = manager.getSubscription(token)

            when (data) {
                is RequestResult.Result -> callback(data.data)
                is RequestResult.Error -> fallback(data.error)
            }
            return
        }
    }

    fun requestPostSubscription(callback: (String) -> Unit, fallback: (Throwable) -> Unit, subscription: SubscriptionPost) {
        val token = FirebaseInstanceId.getInstance().token
        token?.let {
            subscription.idDevice = token

            val data = manager.postSubscription(subscription)

            when (data) {
                is RequestResult.Result -> callback(data.data)
                is RequestResult.Error -> fallback(data.error)
            }
        }
    }

    fun requestDeleteSubscription(callback: (String) -> Unit, fallback: (Throwable) -> Unit, idSubscription: String) {
        val data = manager.deleteSubscription(idSubscription)

        when (data) {
            is RequestResult.Result -> callback(data.data)
            is RequestResult.Error -> fallback(data.error)
        }
    }

}