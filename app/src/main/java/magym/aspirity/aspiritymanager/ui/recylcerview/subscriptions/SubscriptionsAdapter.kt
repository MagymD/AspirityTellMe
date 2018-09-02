package magym.aspirity.aspiritymanager.ui.recylcerview.subscriptions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.request.RequestSubscription

class SubscriptionsAdapter(private val filters: MutableList<RequestSubscription>,
                           private val onClickFilter: OnClickSubscription) : RecyclerView.Adapter<SubscriptionsViewHolder>() {

    init {
        // notifyDataSetChanged()
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false)
        return SubscriptionsViewHolder(v)
    }

    override fun onBindViewHolder(holder: SubscriptionsViewHolder, position: Int) {
        holder.bind(filters, position)

        holder.layout.setOnClickListener { onClickFilter.openSubscription(filters[position].idSubscription) }
    }

    override fun getItemId(position: Int): Long = filters[position].idFilter.hashCode().toLong()

    override fun getItemCount() = filters.size

}