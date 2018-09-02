package magym.aspirity.aspiritymanager.ui.recylcerview.template

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_filter_template.view.*
import magym.aspirity.aspiritymanager.model.Template

class TemplatesViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    internal val layout = v.item_filter_template_layout
    private val title = v.item_filter_template_title

    internal fun bind(templates: List<Template>, position: Int) {
        title.text = templates[position].titleTemplate

        setEnable(templates[position].enable)
    }

    private fun setEnable(enable: String) {
        if (enable == "") title.setTextColor(Color.BLACK)
        else title.setTextColor(Color.GRAY)
    }

}