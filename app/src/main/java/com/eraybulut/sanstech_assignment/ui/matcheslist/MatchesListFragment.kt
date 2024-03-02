package com.eraybulut.sanstech_assignment.ui.matcheslist

import androidx.fragment.app.viewModels
import com.eraybulut.sanstech_assignment.base.BaseFragment
import com.eraybulut.sanstech_assignment.databinding.FragmentMatchesListBinding
import com.eraybulut.sanstech_assignment.ui.matcheslist.adapter.CompetitionHeaderAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MatchesListFragment : BaseFragment<FragmentMatchesListBinding, MatchesListViewModel>(
    FragmentMatchesListBinding::inflate
) {

    override val viewModel: MatchesListViewModel by viewModels()

    @Inject
    lateinit var competitionHeaderAdapter: CompetitionHeaderAdapter

    override fun onCreateFinished() {
        setupMatchAdapter()
    }

    private fun setupMatchAdapter() {
        with(binding.rvMatches) {
            setHasFixedSize(true)
            adapter = competitionHeaderAdapter
        }
    }
}