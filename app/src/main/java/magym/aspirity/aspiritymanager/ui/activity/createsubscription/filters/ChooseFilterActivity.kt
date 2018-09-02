package magym.aspirity.aspiritymanager.ui.activity.createsubscription.filters

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_create_subscription.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.model.Filter
import magym.aspirity.aspiritymanager.ui.activity.createsubscription.templates.ChooseTemplateActivity
import magym.aspirity.aspiritymanager.ui.recylcerview.filter.FiltersAdapter
import magym.aspirity.aspiritymanager.ui.recylcerview.filter.OnClickFilter
import magym.aspirity.aspiritymanager.utils.init
import magym.aspirity.aspiritymanager.utils.showExceptionExt
import magym.aspirity.aspiritymanager.utils.showSnackbarExt

class ChooseFilterActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
        OnClickFilter, IChooseFilterView {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val presenter = ChooseFilterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_subscription)

        supportActionBar?.init(getString(R.string.select_the_filter))
        swipeRefreshLayout = create_description_swipe_refresh_layout.init(this)
        swipeRefreshLayout.isEnabled = false

        presenter.loadFilters()
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

    override fun clickItem(idFilter: String) {
        val intent = Intent(this, ChooseTemplateActivity::class.java)
        intent.putExtra(ModelEn.ID_FILTER.const, idFilter)
        startActivity(intent)
    }

    override fun createRecycler(filters: MutableList<Filter>) {
        recycler_view_filters_templates.layoutManager = LinearLayoutManager(this)
        recycler_view_filters_templates.adapter = FiltersAdapter(filters, this)
    }

    override fun showSnackbar(resId: Int) {
        recycler_view_filters_templates.showSnackbarExt(getString(resId))
    }

    override fun showException(t: Throwable) {
        this.showExceptionExt(recycler_view_filters_templates, t)
    }

}