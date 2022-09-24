package es.wokis.oompaloompas.ui.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.data.constants.ResultCodes
import es.wokis.oompaloompas.databinding.DialogFilterBinding
import es.wokis.oompaloompas.ui.base.dialog.BaseBottomSheetDialogFragment
import es.wokis.oompaloompas.ui.filter.adapter.FilterAdapter
import es.wokis.oompaloompas.ui.filter.viemodel.FilterViewModel
import es.wokis.oompaloompas.utils.hide
import es.wokis.oompaloompas.utils.show

@AndroidEntryPoint
class FilterDialogFragment : BaseBottomSheetDialogFragment() {

    private val viewModel: FilterViewModel by viewModels()
    private var binding: DialogFilterBinding? = null
    private var adapter: FilterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFilterBinding.inflate(layoutInflater, container, false)
        setUpListeners()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expandView()
        setUpAdapter()
        setUpObservers()
    }

    private fun setUpAdapter() {
        binding?.apply {
            adapter = FilterAdapter()
            adapter?.setListener {
                viewModel.saveSelection(it)
            }
            filterListFilters.adapter = adapter
        }
    }

    private fun setUpObservers() {
        observeFilters()
        observeFiltersSaved()
    }

    private fun observeFiltersSaved() {
        viewModel.getFiltersSavedLiveData().observe(viewLifecycleOwner) {
            setFragmentResult(ResultCodes.LIST_TO_FILTER, bundleOf())
            navigateBack()
        }
    }

    private fun observeFilters() {
        viewModel.getFiltersLiveData().observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            binding?.filterContainerMainContent.show()
        }
    }

    private fun setUpListeners() {
        binding?.apply {
            filterBtnClose.setOnClickListener {
                navigateBack()
            }

            filterBtnProfession.setOnClickListener {
                viewModel.getProfessions()
                hideOptionsButtons()
            }

            filterBtnGender.setOnClickListener {
                viewModel.getGenders()
                hideOptionsButtons()
            }

            filterBtnAccept.setOnClickListener {
                showOptionsButtons()
            }

            filterBtnApplyFilter.setOnClickListener {
                viewModel.saveFilters()
            }
        }
    }

    private fun showOptionsButtons() {
        binding?.apply {
            filterBtnProfession.show()
            filterBtnGender.show()
            filterBtnApplyFilter.show()
            filterContainerMainContent.hide()
        }
    }

    private fun hideOptionsButtons() {
        binding?.apply {
            filterBtnProfession.hide()
            filterBtnGender.hide()
            filterBtnApplyFilter.hide()
        }
    }
}