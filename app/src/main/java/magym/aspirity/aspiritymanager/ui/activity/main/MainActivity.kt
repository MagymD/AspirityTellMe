package magym.aspirity.aspiritymanager.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar_main.*
import kotlinx.android.synthetic.main.content_main.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ViewEn
import magym.aspirity.aspiritymanager.ui.activity.createsubscription.filters.ChooseFilterActivity
import magym.aspirity.aspiritymanager.ui.fragment.events.IEventFragment
import magym.aspirity.aspiritymanager.ui.fragment.subscriptions.ISubscriptionsFragment
import magym.aspirity.aspiritymanager.utils.*

class MainActivity : AppCompatActivity(),
        SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener,
        IEventFragment, ISubscriptionsFragment, IMainView {

    private lateinit var myToolbar: Toolbar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var navigationMenu: Menu

    private val newSubscription by lazy { intent.getIntExtra(ViewEn.NEW_SUBSCRIPTION.const, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setVisibilityFab(false)
        myToolbar = toolbar.init(this)
        swipeRefreshLayout = main_swipe_refresh_layout.init(this)
        navigationMenu = navigation_view.init(myToolbar, drawer_layout, this, this)

        presenter = MainPresenter(this, fragmentManager)

        if (newSubscription == 0) itemSelect(R.id.menu_events)
        else itemSelect(R.id.menu_my_filters)

        hasConnection()

        fab.setOnClickListener { presenter!!.startChooseFilterActivity() }
    }

    override fun onResume() {
        super.onResume()
        isRun = true
    }

    override fun onPause() {
        super.onPause()
        isRun = false
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setTitleToolbar(resId: Int) {
        myToolbar.title = getString(resId)
    }

    override fun onRefresh() = presenter!!.requestData(false)

    override fun setRefreshing(refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        presenter!!.chooseMenuItem(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun itemSelect(id: Int) {
        if (!presenter!!.chooseMenuItem(id)) return
        navigationMenu.findItem(id)?.isChecked = true
    }

    override fun setVisibilityFab(visibility: Boolean) {
        if (visibility) fab.visibility = View.VISIBLE
        else fab.visibility = View.GONE
    }

    override fun openSubscription(idSubscription: String) {
        this.openSubscriptionExt(idSubscription)
    }

    override fun openEvent(idEvent: Long) {
        this.openEventExt(idEvent)
    }

    override fun showSnackbar(resId: Int) {
        main_fragment_container.showSnackbarExt(getString(resId))
    }

    override fun showSnackbarWithButton(resId: Int, buttonTitle: String, funAction: () -> Unit): Snackbar {
        return main_fragment_container.showSnackbarExt(getString(resId), buttonTitle, funAction)
    }

    override fun startChooseFilterActivity() = startActivity(Intent(this, ChooseFilterActivity::class.java))

    override fun hasConnection(): Boolean {
        return this.showExceptionExt(main_fragment_container)
    }

    override fun showException(t: Throwable) {
        this.showExceptionExt(main_fragment_container, t)
    }

    companion object { // fixme: LiveData

        private var presenter: MainPresenter? = null

        private var isRun = false

        fun requestData(push: Boolean) {
            if (isRun && presenter != null) presenter?.requestData(push)
        }
    }

}
