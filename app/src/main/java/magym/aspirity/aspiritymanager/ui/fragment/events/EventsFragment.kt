package magym.aspirity.aspiritymanager.ui.fragment.events

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list.view.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.model.request.RequestEvent
import magym.aspirity.aspiritymanager.ui.recylcerview.events.EventsAdapter
import magym.aspirity.aspiritymanager.ui.recylcerview.events.OnClickEvent

class EventsFragment : Fragment(), IEventView, OnClickEvent {

    private lateinit var recyclerView: RecyclerView
    private val events: MutableList<RequestEvent> = ArrayList()

    private val presenter = EventsPresenter(this)
    private var listener: IEventFragment? = null

    private var idSubscription: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IEventFragment) listener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { idSubscription = it.getString(ModelEn.ID_SUBSCRIPTION.const) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.list, container, false)

        recyclerView = v.recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = EventsAdapter(events, this@EventsFragment, idSubscription)
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        presenter.readDb(idSubscription)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        setRefreshing(false)
    }

    fun requestData(idSubscription: String = "") = presenter.requestData(idSubscription)

    override fun openEvent(idEvent: Long) {
        listener?.openEvent(idEvent)
    }

    override fun showSnackbar(resId: Int) {
        listener?.showSnackbar(resId)
    }

    override fun setRefreshing(refreshing: Boolean) {
        listener?.setRefreshing(refreshing)
    }

    override fun hasConnection(): Boolean {
        return listener?.hasConnection()!!
    }

    override fun updateList(newList: MutableList<RequestEvent>) {
        events.clear()
        events.addAll(newList)
        recyclerView.adapter.notifyDataSetChanged()
    }

}