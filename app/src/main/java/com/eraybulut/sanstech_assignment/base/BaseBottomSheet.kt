package com.eraybulut.sanstech_assignment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

abstract class BaseBottomSheet<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected open fun onCreateFinished (){}

    protected open fun initializeListeners() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateFinished()
        initializeListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}