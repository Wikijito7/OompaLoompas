package es.wokis.oompaloompas.ui.detail.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.R
import es.wokis.oompaloompas.data.bo.OompaLoompaBO
import es.wokis.oompaloompas.data.constants.AppConstants.EMPTY_TEXT
import es.wokis.oompaloompas.data.response.AsyncResult
import es.wokis.oompaloompas.databinding.DialogOompaLoompaDetailBinding
import es.wokis.oompaloompas.ui.base.dialog.BaseBottomSheetDialogFragment
import es.wokis.oompaloompas.ui.detail.viemwodel.OompaDetailViewModel
import es.wokis.oompaloompas.utils.capitalize
import es.wokis.oompaloompas.utils.setVisible

@AndroidEntryPoint
class OompaDetailDialogFragment : BaseBottomSheetDialogFragment() {

    private var binding: DialogOompaLoompaDetailBinding? = null

    private val args: OompaDetailDialogFragmentArgs by navArgs()
    private val viewModel: OompaDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogOompaLoompaDetailBinding.inflate(layoutInflater, container, false)
        setUpListeners()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getOompaLoompaInfo(args.oompaId)
    }

    private fun setUpListeners() {
        binding?.oompaDetailBtnClose?.setOnClickListener {
            navigateBack()
        }

        binding?.oompaDetailLabelDescription?.setOnClickListener {
            binding?.oompaDetailLabelDescription?.maxLines = Integer.MAX_VALUE
        }

        binding?.oompaDetailLabelSong?.setOnClickListener {
            binding?.oompaDetailLabelSong?.maxLines = Integer.MAX_VALUE
        }

        binding?.oompaDetailLabelQuota?.setOnClickListener {
            binding?.oompaDetailLabelQuota?.maxLines = Integer.MAX_VALUE
        }
    }

    private fun setUpObservers() {
        observeOompaLoompaInfo()
    }

    private fun observeOompaLoompaInfo() {
        viewModel.getOompaLoompasLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AsyncResult.Error -> {
                    setLoading(false)
                    showErrorDialog(getString(R.string.error__oompa_detail))
                }

                is AsyncResult.Loading -> setLoading(
                    true,
                    getString(R.string.oompa_detail__loading)
                )

                is AsyncResult.Success -> {
                    setLoading(false)
                    it.data?.let { oompaInfo ->
                        setUpView(oompaInfo)
                    }
                }
            }
        }
    }

    private fun setUpView(oompaInfo: OompaLoompaBO) {
        binding?.apply {
            setUpImage(oompaInfo.image)
            oompaDetailLabelFullName.text =
                getString(R.string.row_oompa__full_name, oompaInfo.firstName, oompaInfo.lastName)
            oompaDetailLabelAge.text = getString(R.string.row_oompa__age, oompaInfo.age)
            oompaDetailLabelDescription.text = oompaInfo.description
            oompaDetailLabelEmail.text = oompaInfo.email
            oompaDetailLabelProfession.text = oompaInfo.profession
            oompaDetailLabelGender.text = getString(
                if (oompaInfo.gender == "M") {
                    R.string.row_oompa__male

                } else {
                    R.string.row_oompa__female
                }
            )
            oompaDetailLabelCountry.text = oompaInfo.country
            oompaDetailLabelHeight.text = getString(R.string.oompa_detail__height, oompaInfo.height)
            oompaDetailLabelFood.text = oompaInfo.favorites?.food.capitalize()
            oompaDetailLabelColor.text = oompaInfo.favorites?.color.capitalize()
            oompaDetailLabelSong.text = oompaInfo.favorites?.song
            oompaDetailLabelQuota.text = oompaInfo.quota
        }
    }

    private fun setUpImage(image: String) {
        context?.let {
            binding?.oompaDetailImageOompaImage?.let { imageView ->
                Glide
                    .with(it)
                    .load(image)
                    .fallback(R.drawable.ic_broken_image)
                    .into(imageView)
            }
        }
    }

    private fun setLoading(visible: Boolean, loadingText: String) {
        binding?.oompaDetailIncludeLoading?.apply {
            root.setVisible(visible)
            loadingLabelLoadingText.text = loadingText
        }
    }

    private fun setLoading(visible: Boolean) {
        setLoading(visible, EMPTY_TEXT)
    }

    private fun showErrorDialog(errorMessage: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(R.string.error_dialog__title)
        dialog.setMessage(errorMessage)
        dialog.setPositiveButton(R.string.error_dialog__accept) { dialogInterface, _ ->
            dialogInterface.dismiss()
            navigateBack()
        }
        dialog.setNegativeButton(R.string.error_dialog__retry) { dialogInterface, _ ->
            viewModel.getOompaLoompaInfo(args.oompaId)
            dialogInterface.dismiss()
        }
        dialog.show()
    }
}