package magym.aspirity.aspiritymanager.network.network

import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.model.Subscription
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.model.other.SubscriptionPost

class NetworkRequestManager {

    internal fun getFiltersData(): RequestResult<List<Filter>> {
        return NetworkManager.api
                .getFilters()
                .map<RequestResult<List<Filter>>> { RequestResult.Result(it) }
                .onErrorReturn { t -> RequestResult.Error(t) }
                .blockingGet()
    }

    internal fun getTemplatesData(): RequestResult<List<Template>> {
        return NetworkManager.api
                .getTemplates()
                .map<RequestResult<List<Template>>> { RequestResult.Result(it) }
                .onErrorReturn { t -> RequestResult.Error(t) }
                .blockingGet()
    }

    internal fun getSubscription(idDevice: String): RequestResult<List<Subscription>> {
        return NetworkManager.api
                .getSubscriptions(idDevice)
                .map<RequestResult<List<Subscription>>> { RequestResult.Result(it) }
                .onErrorReturn { t -> RequestResult.Error(t) }
                .blockingGet()
    }

    internal fun postSubscription(subscription: SubscriptionPost): RequestResult<String> {
        return NetworkManager.api
                .postSubscription(subscription)
                .map<RequestResult<String>> { RequestResult.Result(it) }
                .onErrorReturn { t -> RequestResult.Error(t) }
                .blockingGet()
    }

    internal fun deleteSubscription(idSubscription: String): RequestResult<String> {
        return NetworkManager.api
                .deleteSubscription(idSubscription)
                .map<RequestResult<String>> { RequestResult.Result(it) }
                .onErrorReturn { t -> RequestResult.Error(t) }
                .blockingGet()
    }

}