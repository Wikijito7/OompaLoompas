package es.wokis.oompaloompas.ui.oompa.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.R
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.data.response.ErrorType
import es.wokis.oompaloompas.databinding.FragmentOompaLoompasListBinding
import es.wokis.oompaloompas.ui.MainActivity
import es.wokis.oompaloompas.ui.base.fragment.BaseFragment
import es.wokis.oompaloompas.ui.oompa.adapter.OompaLoompaListAdapter
import es.wokis.oompaloompas.ui.oompa.viewmodel.OompaLoompasListViewModel

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
    }

    private fun setUpObservers() {
        setUpOompaLoompasListObserver()
    }

    private fun setUpOompaLoompasListObserver() {
        viewModel.getOompaLoompasLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AsyncResult.Error -> {
                    showErrorDialog(
                        when (it.error) {
                            is ErrorType.DataParseError -> TODO()
                            is ErrorType.NoConnectionError -> TODO()
                            is ErrorType.ServerError -> TODO()
                            is ErrorType.UnknownError -> TODO()
                        }
                    )
                }

                is AsyncResult.Success -> {
                    it.data?.let { oompasList ->
                        adapter?.submitList(oompasList)
                    }
                }
            }
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(R.string.error_dialog__title)
        dialog.setMessage(errorMessage)
        dialog.setPositiveButton(R.string.error_dialog__accept) { dialogInterface, _ ->

            dialogInterface.dismiss()
        }
        dialog.setNegativeButton(R.string.error_dialog__retry) { dialogInterface, _ ->

            dialogInterface.dismiss()
        }
    }

    private fun setUpClickListener() {
        TODO("Not yet implemented")
    }
}