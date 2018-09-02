package magym.aspirity.aspiritymanager.ui.recylcerview.filter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_filter_template.view.*
import magym.aspirity.aspiritymanager.model.Filter

class FiltersViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    internal val layout = v.item_filter_template_layout
    private val title = v.item_filter_template_title

    internal fun bind(filters: List<Filter>, position: Int) {
        title.text = filters[position].titleFilter
    }

}