package magym.aspirity.aspiritymanager.ui.recylcerview.subscriptions

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_subscription.view.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.request.RequestSubscription

class SubscriptionsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    internal val layout = v.item_subscription_layout
    private val titleFilter = v.item_subscription_title_filter
    private val titleTemplate = v.item_subscription_title_template
    private val counter = v.item_subscription_counter

    internal fun bind(subscriptions: List<RequestSubscription>, position: Int) {
        val subscription = subscriptions[position]

        titleFilter.text = subscription.titleFilter
        titleTemplate.text = subscription.titleTemplate
        setCounter(subscription.countEvents)
    }

    private fun setCounter(countEvents: Int) {
        if (countEvents == 0) counter.visibility = View.GONE
        else {
            counter.visibility = View.VISIBLE
            if (countEvents > 99) counter.text = itemView.context.getString(R.string.the_maximum_of_the_counter)
            else counter.text = countEvents.toString()
        }
    }

}