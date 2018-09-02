package magym.aspirity.aspiritymanager.ui.activity.subscription

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.github.marlonlom.utilities.timeago.TimeAgo
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.subscribe_dialog.view.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.model.request.RequestSubscription
import magym.aspirity.aspiritymanager.ui.fragment.events.IEventFragment
import magym.aspirity.aspiritymanager.utils.*

class SubscriptionActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
        ISubscriptionView, IEventFragment {

    private val swipeRefreshLayout by lazy { filter_swipe_refresh_layout.init(this) }

    private lateinit var subscription: RequestSubscription
    private var countSubscription: Int = 0 // fixme: Обновлять при получении нового уведомления

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        idSubscription = intent.getStringExtra(ModelEn.ID_SUBSCRIPTION.const)
        presenter = SubscriptionPresenter(this, fragmentManager).apply {
            addEventFragment(idSubscription)
            initSubscription(idSubscription)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.subscription_toolbar, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        isRun = true
    }

    override fun onPause() {
        super.onPause()
        isRun = false
    }

    override fun initSubscription(sub: RequestSubscription, count: Int) {
        subscription = sub
        countSubscription = count
        supportActionBar?.init(subscription.titleFilter)
        supportActionBar?.subtitle = subscription.titleTemplate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu_subscription_description -> alertDialogDescription()
            R.id.menu_subscription_unsubscribe -> alertDialogUnsubscribe(subscription.idSubscription)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun openMainActivity() = this.openMainActivityExt()

    override fun onRefresh() = presenter!!.requestData(idSubscription)

    override fun setRefreshing(refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }

    override fun openEvent(idEvent: Long) {
        this.openEventExt(idEvent)
    }

    override fun showSnackbar(resId: Int) {
        filter_fragment_container.showSnackbarExt(getString(resId))
    }

    override fun hasConnection(): Boolean {
        return this.showExceptionExt(filter_fragment_container)
    }

    override fun showException(t: Throwable) {
        this.showExceptionExt(filter_fragment_container, t)
    }

    private fun alertDialogDescription() {
        val filterDialog = layoutInflater.inflate(R.layout.subscribe_dialog, null) as LinearLayout

        with(filterDialog) {
            item_filter_dialog_date.text = String.format(getString(R.string.time_time_ago), TimeAgo.using(subscription.dateAdd), countSubscription)
            item_subscribe_dialog_filter_title.text = subscription.titleFilter
            item_subscribe_dialog_filter_description.text = setDescription(subscription.descriptionFilter)
            item_subscribe_dialog_template_title.text = subscription.titleTemplate
            item_subscribe_dialog_template_description.text = setDescription(subscription.descriptionTemplate)
        }

        val builder = AlertDialog.Builder(this)
        builder.setView(filterDialog)
                .setPositiveButton(getString(R.string.close)) { _, _ -> }
                .show()
    }

    private fun setDescription(text: String): String {
        return if (TextUtils.isEmpty(text)) getString(R.string.no_description_available)
        else text
    }

    private fun alertDialogUnsubscribe(idSubscription: String) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(getString(R.string.unsubscribe_question))
                .setPositiveButton(getString(R.string.unsubscribe)) { _, _ -> presenter!!.deleteSubscription(idSubscription) }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }

        val alertDialog = builder.create()
        alertDialog.show()

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.GRAY)
    }

    companion object {

        private var idSubscription: String = ""

        private var presenter: SubscriptionPresenter? = null

        private var isRun = false

        fun requestData(idSubscription: String) {
            if (isRun && presenter != null && this.idSubscription == idSubscription) presenter?.requestData(idSubscription)
        }

    }

}