package magym.aspirity.aspiritymanager.ui.activity.createsubscription.filters

import magym.aspirity.aspiritymanager.model.Filter

interface IChooseFilterView {

    fun myOnBackPressed()

    fun setRefreshing(refreshing: Boolean)

    fun createRecycler(filters: MutableList<Filter>)

    fun showSnackbar(resId: Int)

    fun showException(t: Throwable)
}