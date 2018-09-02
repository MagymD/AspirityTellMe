package magym.aspirity.aspiritymanager.ui.activity.subscription

import magym.aspirity.aspiritymanager.model.request.RequestSubscription

interface ISubscriptionView {

    fun openMainActivity()

    fun initSubscription(sub: RequestSubscription, count: Int)

    fun showSnackbar(resId: Int)

    fun hasConnection(): Boolean

    fun showException(t: Throwable)
}