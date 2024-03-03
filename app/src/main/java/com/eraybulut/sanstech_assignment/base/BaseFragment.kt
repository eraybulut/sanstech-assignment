package com.eraybulut.sanstech_assignment.base

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.eraybulut.sanstech_assignment.R
import com.eraybulut.sanstech_assignment.utils.extensions.collect
import com.eraybulut.sanstech_assignment.utils.extensions.showToast

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    private val loadingDialog: AlertDialog by lazy { createLoadingDialog() }

    protected abstract val viewModel: VM
    protected open fun onCreateFinished() {}
    protected open fun initializeListeners() {}
    protected open fun observeEvents() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        onCreateFinished()
        initializeListeners()
        observeEvents()
        collect(viewModel.loading, ::handleLoadingStatus)
        collect(viewModel.baseEvent, ::onViewEvent)
    }

    override fun onResume() {
        super.onResume()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun handleLoadingStatus(isLoading: Boolean) {
        if (isLoading) {
            loadingDialog.show()
        } else {
            loadingDialog.cancel()
            loadingDialog.dismiss()
        }
    }

    private fun createLoadingDialog(): AlertDialog {
        val loadingView = LayoutInflater.from(requireContext()).inflate(R.layout.view_loading, null)
        return AlertDialog.Builder(requireContext()).setView(loadingView).setCancelable(false)
            .create().apply {
                window?.apply {
                    setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    )
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    )
                }
            }
    }

    private fun onViewEvent(onViewEvent: BaseViewEvent) {
        when (onViewEvent) {
            is BaseViewEvent.ShowToast<*> -> {
                when (val message = onViewEvent.message) {
                    is String -> requireContext().showToast(message)
                    is Int -> requireContext().showToast(getString(message))
                    else -> throw IllegalArgumentException("Unsupported message type")
                }
            }

        }
    }

    private fun showMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        requireContext().showToast(message, duration)
    }

    private fun showMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        showMessage(message = getString(message), duration = duration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}