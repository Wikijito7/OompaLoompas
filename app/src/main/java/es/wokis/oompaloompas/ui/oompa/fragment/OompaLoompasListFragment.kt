package es.wokis.oompaloompas.ui.oompa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.databinding.FragmentOompaLoompasListBinding
import es.wokis.oompaloompas.ui.oompa.viewmodel.OompaLoompasListViewModel

@AndroidEntryPoint
class OompaLoompasListFragment : Fragment() {

    private val viewModel: OompaLoompasListViewModel by viewModels()

    private var binding: FragmentOompaLoompasListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOompaLoompasListBinding.inflate(inflater, container, false)
        setUpClickListener()
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

    private fun setUpObservers() {
        setUpOompaLoompasListObserver()
    }

    private fun setUpOompaLoompasListObserver() {
        TODO("Not yet implemented")
    }

    private fun setUpClickListener() {
        TODO("Not yet implemented")
    }
}