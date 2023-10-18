package com.cursosant.inventorybase.addModule.view

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cursosant.inventorybase.R
import com.cursosant.inventorybase.addModule.viewModel.AddViewModel
import com.cursosant.inventorybase.databinding.FragmentAddProductBinding
import com.cursosant.inventorybase.entities.Product
import kotlin.random.Random


class AddProductFragment : DialogFragment(), DialogInterface.OnShowListener {
    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAddProductBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity()).apply {
            setTitle(R.string.add_title)
            setPositiveButton(R.string.add_dialog_ok, null)
            setNegativeButton(R.string.add_dialog_cancel, null)
            setView(binding.root)
        }

        setupTextFields()

        setupViewModel()

        val dialog = builder.create()
        dialog.setOnShowListener(this)

        return dialog
    }

    private fun setupTextFields() {
        binding.etPhotoUrl.addTextChangedListener {
            val photoUrl = binding.etPhotoUrl.text.toString().trim()
            if (photoUrl.isEmpty()){
                binding.imgPhoto.setImageDrawable(null)
            } else {
                val options = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                Glide.with(requireActivity())
                    .load(photoUrl)
                    .apply(options)
                    .into(binding.imgPhoto)
            }
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(requireActivity()).get(AddViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFocus()

        setupObservers()
    }

    private fun setupFocus() {
        binding.etName.requestFocus()
    }

    private fun setupObservers(){
        viewModel.getResult().observe(viewLifecycleOwner, { result ->
            if (result) dismiss()
        })
        viewModel.isInProgress().observe(viewLifecycleOwner, { inProgress ->
            binding.progressBar.visibility = if (inProgress) View.VISIBLE else View.GONE
        })
    }

    override fun onShow(p0: DialogInterface?) {
        val dialog = dialog as? AlertDialog
        dialog?.let {
            val positiveButton = it.getButton(DialogInterface.BUTTON_POSITIVE)
            val negativeButton = it.getButton(DialogInterface.BUTTON_NEGATIVE)
            positiveButton.setOnClickListener {
                if (validateProduct()){
                    val product = Product(
                        id = Random.nextLong(100, 1_000),
                        name = binding.etName.text.toString().trim(),
                        quantity = binding.etQuantity.text.toString().trim().toInt(),
                        photoUrl = binding.etPhotoUrl.text.toString().trim()
                    )
                    viewModel.addProduct(product)
                }
            }
            negativeButton.setOnClickListener { dismiss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateProduct(): Boolean {
        var isValid = true
        with(binding) {
            if (etQuantity.text.toString().trim().isEmpty()) {
                etQuantity.error =
                    requireActivity().getString(R.string.common_validate_field_required)
                etQuantity.requestFocus()
                isValid = false
            } else if (etQuantity.text.toString().trim().toInt() <= 0) {
                etQuantity.error =
                    requireActivity().getString(R.string.common_validate_min_quantity)
                etQuantity.requestFocus()
                isValid = false
            }
            if (etPhotoUrl.text.toString().trim().isEmpty()) {
                etPhotoUrl.error =
                    requireActivity().getString(R.string.common_validate_field_required)
                etPhotoUrl.requestFocus()
                isValid = false
            }
            if (etName.text.toString().trim().isEmpty()) {
                etName.error = requireActivity().getString(R.string.common_validate_field_required)
                etName.requestFocus()
                isValid = false
            }
        }
        return isValid
    }
}