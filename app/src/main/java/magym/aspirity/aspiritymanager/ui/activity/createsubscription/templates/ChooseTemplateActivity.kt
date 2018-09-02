package magym.aspirity.aspiritymanager.ui.activity.createsubscription.templates

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_create_subscription.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.model.Template
import magym.aspirity.aspiritymanager.ui.recylcerview.template.OnClickTemplate
import magym.aspirity.aspiritymanager.ui.recylcerview.template.TemplatesAdapter
import magym.aspirity.aspiritymanager.utils.init
import magym.aspirity.aspiritymanager.utils.openMainActivityExt
import magym.aspirity.aspiritymanager.utils.showExceptionExt
import magym.aspirity.aspiritymanager.utils.showSnackbarExt

class ChooseTemplateActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
        OnClickTemplate, IChooseTemplateView {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val presenter by lazy { ChooseTemplatePresenter(this) }

    private val idFilter by lazy { intent.getStringExtra(ModelEn.ID_FILTER.const) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_subscription)

        supportActionBar?.init(getString(R.string.select_template))
        swipeRefreshLayout = create_description_swipe_refresh_layout.init(this)
        swipeRefreshLayout.isEnabled = false

        presenter.loadTemplates(idFilter)
    }

    override fun myOnBackPressed() {
        onBackPressed()
    }

    override fun onRefresh() {}

    override fun setRefreshing(refreshing: Boolean) {
        swipeRefreshLayout.isEnabled = refreshing
        swipeRefreshLayout.isRefreshing = refreshing
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun clickItem(idTemplate: String) = presenter.createSubscription(idFilter, idTemplate)

    override fun createRecycler(templates: MutableList<Template>) {
        recycler_view_filters_templates.layoutManager = LinearLayoutManager(this)
        recycler_view_filters_templates.adapter = TemplatesAdapter(templates, this)
    }

    override fun openMainActivity() = this.openMainActivityExt()

    override fun showSnackbar(resId: Int) {
        recycler_view_filters_templates.showSnackbarExt(getString(resId))
    }

    override fun showException(t: Throwable) {
        this.showExceptionExt(recycler_view_filters_templates, t)
    }

}