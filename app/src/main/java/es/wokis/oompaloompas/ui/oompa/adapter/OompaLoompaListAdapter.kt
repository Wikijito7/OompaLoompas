package es.wokis.oompaloompas.ui.oompa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.wokis.oompaloompas.R
import es.wokis.oompaloompas.data.constants.AppConstants.MALE
import es.wokis.oompaloompas.databinding.RowOompaLoompaBinding
import es.wokis.oompaloompas.ui.oompa.vo.OompaLoompaVO

class OompaLoompaListAdapter :
    ListAdapter<OompaLoompaVO, OompaLoompaListAdapter.ViewHolder>(DiffUtils()) {

    private var onItemClickListener: (id: Long) -> Unit = {
        // no-op
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowOompaLoompaBinding =
            RowOompaLoompaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOompaLoompa = currentList[position]
        holder.bind(currentOompaLoompa, onItemClickListener)
    }

    fun setOnClickListener(onClickListener: (id: Long) -> Unit) {
        this.onItemClickListener = onClickListener
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

        fun bind(oompaLoompa: OompaLoompaVO, listener: (id: Long) -> Unit) {
            setUpLabels(oompaLoompa)
            setUpImage(oompaLoompa)
            setUpClickListener(oompaLoompa.id, listener)
        }

        private fun setUpLabels(oompaLoompa: OompaLoompaVO) {
            val context = binding.root.context
            binding.apply {
                rowOompaLabelFullName.text = context.getString(
                    R.string.row_oompa__full_name,
                    oompaLoompa.firstName,
                    oompaLoompa.lastName
                )
                rowOompaLabelProfession.text = oompaLoompa.profession
                rowOompaLabelEmail.text = oompaLoompa.email
                rowOompaLabelAge.text = context.getString(R.string.row_oompa__age, oompaLoompa.age)
                rowOompaLabelGender.text =
                    context.getString(
                        if (oompaLoompa.gender == MALE) {
                            R.string.row_oompa__male

                        } else {
                            R.string.row_oompa__female
                        }
                    )
            }
        }

        private fun setUpImage(oompaLoompa: OompaLoompaVO) {
            Glide
                .with(binding.root)
                .load(oompaLoompa.image)
                .fallback(R.drawable.ic_broken_image)
                .into(binding.rowOompaImageOompaImage)
        }

        private fun setUpClickListener(id: Long?, listener: (id: Long) -> Unit) {
            binding.root.setOnClickListener {
                id?.let {
                    listener(id)
                }
            }
        }
    }
}