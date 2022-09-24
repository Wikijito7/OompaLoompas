package es.wokis.oompaloompas.ui.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.wokis.oompaloompas.databinding.RowFilterBinding
import es.wokis.oompaloompas.ui.filter.vo.FilterVO

class FilterAdapter : ListAdapter<FilterVO, FilterAdapter.ViewHolder>(FiltersDiffUtils()) {

    private var listener: (filter: FilterVO) -> Unit = {
        // no-op
    }

    private var selectedRadio: RadioButton? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowFilterBinding =
            RowFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = currentList[position]
        holder.bind(filter)
    }

    fun setListener(listener: (filter: FilterVO) -> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: RowFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: FilterVO) {
            setUpView(filter)
            setUpClickListener(filter)
        }

        private fun setUpClickListener(filter: FilterVO) {
            binding.root.setOnClickListener {
                listener(filter.copy(selected = true))
                updateSelectedRadio()
            }

            binding.rowFilterRadioItemName.setOnClickListener {
                listener(filter.copy(selected = true))
                updateSelectedRadio()
            }
        }

        private fun updateSelectedRadio() {
            selectedRadio?.isChecked = false
            selectedRadio = binding.rowFilterRadioItemName
        }

        private fun setUpView(filter: FilterVO) {
            binding.rowFilterRadioItemName.apply {
                text = filter.name
                isChecked = filter.selected
                if (filter.selected) {
                    selectedRadio = this
                }
            }
        }
    }

    class FiltersDiffUtils : DiffUtil.ItemCallback<FilterVO>() {
        override fun areItemsTheSame(oldItem: FilterVO, newItem: FilterVO): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: FilterVO, newItem: FilterVO): Boolean =
            oldItem.name == newItem.name &&
                    oldItem.selected == newItem.selected &&
                    oldItem.type == newItem.type

    }
}