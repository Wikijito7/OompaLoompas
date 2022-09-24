package es.wokis.oompaloompas.ui.base.dialog

import android.view.View
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    fun navigateBack() {
        findNavController().navigateUp()
    }

    fun expandView() {
        (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

}