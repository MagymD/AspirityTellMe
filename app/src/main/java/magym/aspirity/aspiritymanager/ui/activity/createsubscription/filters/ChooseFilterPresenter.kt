package magym.aspirity.aspiritymanager.ui.activity.createsubscription.filters

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.network.network.DataRequestManager
import java.util.concurrent.TimeUnit

class ChooseFilterPresenter(private val view: IChooseFilterView) {

    private val requestData = DataRequestManager()
    private val model = ChooseFilterModel()

    internal fun loadFilters() {
        view.setRefreshing(true)

        launch {
            requestData.requestGetFilters({
                if (it.isEmpty()) view.showSnackbar(R.string.the_filters_list_is_missing)

                model.updateFilters(it)

                launch(UI) {
                    view.createRecycler(it as MutableList<Filter>)
                    view.setRefreshing(false)
                }
            }, {
                view.showException(it)
                TimeUnit.MILLISECONDS.sleep(1500)
                launch(UI) { view.myOnBackPressed() }
            })
        }
    }

}
