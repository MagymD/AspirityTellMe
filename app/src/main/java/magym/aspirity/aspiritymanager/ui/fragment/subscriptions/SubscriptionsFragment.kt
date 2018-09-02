package magym.aspirity.aspiritymanager.ui.fragment.subscriptions

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list.view.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.request.RequestSubscription
import magym.aspirity.aspiritymanager.ui.recylcerview.subscriptions.OnClickSubscription
import magym.aspirity.aspiritymanager.ui.recylcerview.subscriptions.SubscriptionsAdapter

class SubscriptionsFragment : Fragment(), ISubscriptionsView, OnClickSubscription {

    private lateinit var recyclerView: RecyclerView
    private val subscriptions: MutableList<RequestSubscription> = ArrayList()

    private val presenter by lazy { SubscriptionsPresenter(this) }
    private var listener: ISubscriptionsFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISubscriptionsFragment) listener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.list, container, false)

        recyclerView = v.recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SubscriptionsAdapter(subscriptions, this@SubscriptionsFragment)
        }

        requestData(false)

        return v
    }

    override fun onResume() {
        super.onResume()
        presenter.readDb(false)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        setRefreshing(false)
    }

    fun requestData(push: Boolean) = presenter.requestDara(push)

    override fun itemSelect(id: Int) {
        listener?.itemSelect(id)
    }

    override fun openSubscription(idSubscription: String) {
        listener?.openSubscription(idSubscription)
    }

    override fun showSnackbar(resId: Int) {
        listener?.showSnackbar(resId)
    }

    override fun showSnackbarWithButton(resId: Int, buttonTitle: String, funAction: () -> Unit): Snackbar {
        return listener?.showSnackbarWithButton(resId, buttonTitle, funAction)!!

    }

    override fun setRefreshing(refreshing: Boolean) {
        listener?.setRefreshing(refreshing)
    }

    override fun hasConnection(): Boolean {
        return listener?.hasConnection()!!
    }

    override fun showException(t: Throwable) {
        listener?.showException(t)
    }

    private fun updateDataSetChanged() = recyclerView.adapter.notifyDataSetChanged()

    override fun updateList(newList: List<RequestSubscription>) {
        subscriptions.clear()
        subscriptions.addAll(newList)
        updateDataSetChanged()
    }

}