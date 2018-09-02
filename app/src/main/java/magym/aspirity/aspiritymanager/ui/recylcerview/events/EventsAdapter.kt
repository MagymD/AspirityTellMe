package magym.aspirity.aspiritymanager.ui.recylcerview.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.request.RequestEvent

class EventsAdapter(private val events: MutableList<RequestEvent>,
                    private val onClickEvent: OnClickEvent,
                    private val idSubscription: String) : RecyclerView.Adapter<EventsViewHolder>() {

    init {
        // notifyDataSetChanged()
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events, position, idSubscription)

        holder.layout.setOnClickListener {
            onClickEvent.openEvent(events[position].idEvent)
        }
    }

    override fun getItemId(position: Int): Long = events[position].idEvent

    override fun getItemCount() = events.size

}