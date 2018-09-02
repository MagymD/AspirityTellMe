package magym.aspirity.aspiritymanager.ui.activity.createsubscription.templates

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.model.other.SubscriptionPost
import magym.aspirity.aspiritymanager.network.network.DataRequestManager
import java.util.concurrent.TimeUnit

class ChooseTemplatePresenter(private val view: IChooseTemplateView) {

    private val requestData = DataRequestManager()
    private val model = ChooseTemplateModel()

    internal fun loadTemplates(idFilter: String) {
        view.setRefreshing(true)

        launch {
            requestData.requestGetTemplates({ it ->
                if (it.isEmpty()) view.showSnackbar(R.string.the_templates_list_is_missing)

                model.updateTemplates(it)

                val job = launch {
                    it.forEach {
                        val enable: String? = model.getEnable(idFilter, it.idTemplate)
                        if (enable != null) it.enable = enable
                    }
                }

                launch(UI) {
                    job.join()
                    view.createRecycler(it as MutableList<Template>)
                    view.setRefreshing(false)
                }
            }, {
                view.showException(it)
                TimeUnit.MILLISECONDS.sleep(1500)
                launch(UI) { view.myOnBackPressed() }
            })
        }
    }

    internal fun createSubscription(idFilter: String, idTemplate: String) {
        val subscription = SubscriptionPost().apply {
            this.idFilter = idFilter
            this.idTemplate = idTemplate
        }


        val job = launch {
            requestData.requestPostSubscription({ }, {
                view.showException(it)
                TimeUnit.MILLISECONDS.sleep(1500)
            }, subscription)
        }

        launch(UI) {
            job.join()
            view.openMainActivity()
        }
    }

}