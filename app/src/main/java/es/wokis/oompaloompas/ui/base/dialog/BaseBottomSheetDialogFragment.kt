package es.wokis.oompaloompas.ui.base.dialog

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    fun navigateBack() {
        findNavController().navigateUp()
    }

}