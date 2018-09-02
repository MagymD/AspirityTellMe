package magym.aspirity.aspiritymanager.ui.activity.createsubscription.templates

import magym.aspirity.aspiritymanager.model.Template

interface IChooseTemplateView {

    fun myOnBackPressed()

    fun setRefreshing(refreshing: Boolean)

    fun createRecycler(templates: MutableList<Template>)

    fun openMainActivity()

    fun showSnackbar(resId: Int)

    fun showException(t: Throwable)
}