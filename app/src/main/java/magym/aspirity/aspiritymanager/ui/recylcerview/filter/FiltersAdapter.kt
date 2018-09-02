package magym.aspirity.aspiritymanager.ui.recylcerview.filter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.model.Filter

class FiltersAdapter(private val filters: MutableList<Filter>,
                     private val onClickFilter: OnClickFilter) : RecyclerView.Adapter<FiltersViewHolder>() {

    init {
        // notifyDataSetChanged()
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_filter_template, parent, false)
        return FiltersViewHolder(v)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bind(filters, position)

        holder.layout.setOnClickListener { onClickFilter.clickItem(filters[position].idFilter) }
    }

    override fun getItemId(position: Int): Long = filters[position].idFilter.hashCode().toLong()

    override fun getItemCount() = filters.size

}