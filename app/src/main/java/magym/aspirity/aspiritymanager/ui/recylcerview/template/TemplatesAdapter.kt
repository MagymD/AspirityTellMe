package magym.aspirity.aspiritymanager.ui.recylcerview.template

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.Template

class TemplatesAdapter(private val templates: MutableList<Template>,
                       private val onClickTemplate: OnClickTemplate) : RecyclerView.Adapter<TemplatesViewHolder>() {

    init {
        // notifyDataSetChanged()
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplatesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_filter_template, parent, false)
        return TemplatesViewHolder(v)
    }

    override fun onBindViewHolder(holder: TemplatesViewHolder, position: Int) {
        holder.bind(templates, position)

        holder.layout.setOnClickListener { if (templates[position].enable == "") onClickTemplate.clickItem(templates[position].idTemplate) }
    }

    override fun getItemId(position: Int): Long = templates[position].idTemplate.hashCode().toLong()

    override fun getItemCount() = templates.size

}