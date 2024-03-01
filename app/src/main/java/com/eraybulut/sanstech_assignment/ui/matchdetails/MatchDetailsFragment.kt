package com.eraybulut.sanstech_assignment.ui.matchdetails

import androidx.fragment.app.viewModels
import com.eraybulut.sanstech_assignment.base.BaseFragment
import com.eraybulut.sanstech_assignment.databinding.FragmentMatchDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsFragment : BaseFragment<FragmentMatchDetailsBinding, MatchDetailsViewModel>(
    FragmentMatchDetailsBinding::inflate
) {

    override val viewModel: MatchDetailsViewModel by viewModels()

}