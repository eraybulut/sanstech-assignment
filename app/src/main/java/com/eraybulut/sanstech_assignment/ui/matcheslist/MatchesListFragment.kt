package com.eraybulut.sanstech_assignment.ui.matcheslist

import androidx.fragment.app.viewModels
import com.eraybulut.sanstech_assignment.base.BaseFragment
import com.eraybulut.sanstech_assignment.databinding.FragmentMatchesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchesListFragment : BaseFragment<FragmentMatchesListBinding, MatchesListViewModel>(
    FragmentMatchesListBinding::inflate
) {

    override val viewModel: MatchesListViewModel by viewModels()

}