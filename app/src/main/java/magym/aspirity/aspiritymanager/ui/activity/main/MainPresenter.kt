package magym.aspirity.aspiritymanager.ui.activity.main

import android.app.Fragment
import android.app.FragmentManager
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ViewEn
import magym.aspirity.aspiritymanager.ui.fragment.events.EventsFragment
import magym.aspirity.aspiritymanager.ui.fragment.subscriptions.SubscriptionsFragment

class MainPresenter(private val view: IMainView,
                    private val fragmentManager: FragmentManager) { // todo: FragmentManager вынести в activity

    private val eventsFragment by lazy { EventsFragment() }
    private val subscriptionsFragment by lazy { SubscriptionsFragment() }

    internal fun requestData(push: Boolean) {
        if (fragmentManager.findFragmentByTag(ViewEn.EVENTS.const) != null) eventsFragment.requestData()
        else subscriptionsFragment.requestData(push)
    }

    internal fun chooseMenuItem(id: Int): Boolean {
        when (id) {
            R.id.menu_events -> {
                openFragment(eventsFragment, ViewEn.EVENTS.const)
                view.setVisibilityFab(false)
                view.setTitleToolbar(R.string.notifications)
            }
            R.id.menu_my_filters -> {
                openFragment(subscriptionsFragment, ViewEn.SUBSCRIPTIONS.const)
                view.setVisibilityFab(true)
                view.setTitleToolbar(R.string.subscriptions)
            }
            else -> return false
        }

        return true
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment, tag)
                .commit()
    }

    internal fun startChooseFilterActivity() {
        if (view.hasConnection()) view.startChooseFilterActivity()
    }

}