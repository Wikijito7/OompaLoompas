package es.wokis.oompaloompas.ui.oompa.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.R
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.ErrorType
import es.wokis.oompaloompas.databinding.FragmentOompaLoompasListBinding
import es.wokis.oompaloompas.ui.base.fragment.BaseFragment
import es.wokis.oompaloompas.ui.oompa.adapter.OompaLoompaListAdapter
import es.wokis.oompaloompas.ui.oompa.viewmodel.OompaLoompasListViewModel
import es.wokis.oompaloompas.utils.hide
import es.wokis.oompaloompas.utils.setVisible
import es.wokis.oompaloompas.utils.show

@AndroidEntryPoint
class OompaLoompasListFragment : BaseFragment() {

    private val viewModel: OompaLoompasListViewModel by viewModels()

    private var binding: FragmentOompaLoompasListBinding? = null
    private var adapter: OompaLoompaListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOompaLoompasListBinding.inflate(inflater, container, false)
        setUpClickListener()
        setUpAdapter()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getOompaLoompas()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onFilterMenuButtonClickedListener(): Boolean {
        navigateTo(OompaLoompasListFragmentDirections.actionListNavigateToFilter())
        return true
    }

    private fun setUpAdapter() {
        adapter = OompaLoompaListAdapter()
        adapter?.setOnClickListener {
            navigateToOompaDetail(it)
        }
        binding?.oompaLoompasListOompas?.adapter = adapter
    }

    private fun navigateToOompaDetail(id: Long) {
        navigateTo(OompaLoompasListFragmentDirections.actionListNavigateToDetail(id))
    }

    private fun setUpObservers() {
        setUpOompaLoompasListObserver()
        setUpMaxPageObserver()
    }

    private fun setUpMaxPageObserver() {
        viewModel.getMaxPageLiveData().observe(viewLifecycleOwner) {
            updateMaxPageIndicator(it)
        }
    }

    private fun updateMaxPageIndicator(maxPage: Int) {
        val currentPage = viewModel.getCurrentPage()
        binding?.apply {
            oompaLoompasLabelPageIndicator.text =
                getString(R.string.oompa_loompas__page_indicator, currentPage, maxPage)
            oompaLoompasBtnPrevious.isEnabled = currentPage > 1
            oompaLoompasBtnNext.isEnabled = currentPage < maxPage
        }
    }

    private fun setUpOompaLoompasListObserver() {
        viewModel.getOompaLoompasLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AsyncResult.Error -> {
                    setLoading(false)
                    showOompaLoompasErrorDialog(
                        getString(
                            when (it.error) {
                                is ErrorType.DataParseError -> R.string.error__data_parse
                                is ErrorType.NoConnectionError -> R.string.error__no_connection
                                is ErrorType.ServerError -> R.string.error__server
                                is ErrorType.UnknownError -> R.string.error__unknown
                            }
                        )
                    )
                    binding?.apply {
                        oompaLoompasContainerMainScroll.hide()
                        oompaLoompasLabelNoOompasFound.show()
                        oompaLoompasBtnRetry.show()
                    }
                }

                is AsyncResult.Success -> {
                    setLoading(false)
                    it.data?.let { oompasList ->
                        binding?.apply {
                            oompaLoompasContainerMainScroll.setVisible(oompasList.isNotEmpty())
                            oompaLoompasLabelNoOompasFound.setVisible(oompasList.isEmpty())
                            oompaLoompasBtnRetry.setVisible(oompasList.isEmpty())
                        }

                        if (oompasList.isNotEmpty()) {
                            adapter?.submitList(oompasList)
                            viewModel.getMaxPage()
                        }
                    }
                }

                is AsyncResult.Loading -> setLoading(
                    true,
                    getString(R.string.oompa_loompas__loading_oompas)
                )
            }
        }
    }

    private fun showOompaLoompasErrorDialog(errorMessage: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(R.string.error_dialog__title)
        dialog.setMessage(errorMessage)
        dialog.setPositiveButton(R.string.error_dialog__accept) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        dialog.setNegativeButton(R.string.error_dialog__retry) { dialogInterface, _ ->
            viewModel.getOompaLoompas()
            dialogInterface.dismiss()
        }
        dialog.show()
    }

    private fun setUpClickListener() {
        binding?.apply {
            oompaLoompasBtnNext.setOnClickListener {
                viewModel.nextPage()
                scrollToTop()
            }

            oompaLoompasBtnPrevious.setOnClickListener {
                viewModel.previousPage()
                scrollToTop()
            }

            oompaLoompasBtnRetry.setOnClickListener {
                viewModel.getOompaLoompas()
            }
        }
    }

    private fun scrollToTop() {
        binding?.oompaLoompasContainerMainScroll?.smoothScrollTo(TOP, LEFT, SCROLL_DURATION)
    }

    companion object {
        private const val TOP = 0
        private const val LEFT = 0
        private const val SCROLL_DURATION = 350
    }
}