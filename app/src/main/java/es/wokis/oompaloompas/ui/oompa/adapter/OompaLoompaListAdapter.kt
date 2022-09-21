package es.wokis.oompaloompas.ui.oompa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.wokis.oompaloompas.databinding.RowOompaLoompaBinding
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO

class OompaLoompaListAdapter :
    ListAdapter<OompaLoompaVO, OompaLoompaListAdapter.ViewHolder>(DiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowOompaLoompaBinding =
            RowOompaLoompaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOompaLoompa = currentList[position]
        holder.bind(currentOompaLoompa)
    }

    class DiffUtils : DiffUtil.ItemCallback<OompaLoompaVO>() {
        override fun areItemsTheSame(
            oldItem: OompaLoompaVO,
            newItem: OompaLoompaVO
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: OompaLoompaVO,
            newItem: OompaLoompaVO
        ): Boolean = oldItem.id == newItem.id
    }

    class ViewHolder(private val binding: RowOompaLoompaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(oompaLoompa: OompaLoompaVO) {

        }
    }
}