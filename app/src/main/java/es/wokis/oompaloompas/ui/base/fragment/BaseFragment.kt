package es.wokis.oompaloompas.ui.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import es.wokis.oompaloompas.ui.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setOnClickMenuListener {
            onFilterMenuButtonClickedListener()
        }
    }

    /**
     * Overrides default functionality when filter button is clicked
     */
    open fun onFilterMenuButtonClickedListener(): Boolean {
        return false
    }

    fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }

}