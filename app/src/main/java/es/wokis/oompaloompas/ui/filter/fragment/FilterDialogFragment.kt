package es.wokis.oompaloompas.ui.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.wokis.oompaloompas.databinding.DialogFilterBinding
import es.wokis.oompaloompas.databinding.DialogOompaLoompaDetailBinding
import es.wokis.oompaloompas.ui.base.dialog.BaseBottomSheetDialogFragment

class FilterDialogFragment : BaseBottomSheetDialogFragment() {

    private var binding: DialogFilterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFilterBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }
}