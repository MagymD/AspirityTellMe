package magym.aspirity.aspiritymanager.ui.recylcerview.events

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.marlonlom.utilities.timeago.TimeAgo
import kotlinx.android.synthetic.main.item_event.view.*
import magym.aspirity.aspiritymanager.model.request.RequestEvent
import magym.aspirity.aspiritymanager.utils.fromHtml

class EventsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    internal val layout = v.item_event_layout
    private val layoutSubscription = v.item_event_subscription
    private val date = v.item_event_date
    private val titleFilter = v.item_event_filter_title
    private val titleTemplate = v.item_event_template_title
    private val description = v.item_event_description

    internal fun bind(events: List<RequestEvent>, position: Int, idSubscription: String) {
        val event = events[position]

        date.text = TimeAgo.using(event.dateEvent.toLong())
        titleFilter.text = event.titleFilter.fromHtml()
        titleTemplate.text = event.titleTemplate.fromHtml()
        description.text = event.descriptionEvent.fromHtml()

        hideTitleEvent(idSubscription)
        setRead(event.readed)
    }

    private fun hideTitleEvent(idSubscription: String) {
        if (idSubscription != "" && layoutSubscription.visibility != View.GONE) {
            layoutSubscription.visibility = View.GONE
        }
    }

    private fun setRead(readed: Int) {
        if (readed == 0) layout.setBackgroundColor(Color.parseColor("#E1F5FE"))
        else layout.setBackgroundColor(Color.WHITE)
    }

}