package com.eraybulut.sanstech_assignment.ui.matcheslist

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.R
import com.eraybulut.sanstech_assignment.base.BaseFragment
import com.eraybulut.sanstech_assignment.databinding.FragmentMatchesListBinding
import com.eraybulut.sanstech_assignment.ui.dialog.matchfilter.MatchesFilterBottomSheet
import com.eraybulut.sanstech_assignment.ui.dialog.matchsort.MatchesSortBottomSheet
import com.eraybulut.sanstech_assignment.ui.matcheslist.adapter.CompetitionHeaderAdapter
import com.eraybulut.sanstech_assignment.utils.enums.MatchStatusTypes
import com.eraybulut.sanstech_assignment.utils.enums.SortOrderTypes
import com.eraybulut.sanstech_assignment.utils.extensions.collect
import com.eraybulut.sanstech_assignment.utils.extensions.fadeIn
import com.eraybulut.sanstech_assignment.utils.extensions.fadeOut
import com.eraybulut.sanstech_assignment.utils.extensions.goStartPosition
import com.eraybulut.sanstech_assignment.utils.extensions.goStartPositionSmooth
import com.eraybulut.sanstech_assignment.utils.extensions.gone
import com.eraybulut.sanstech_assignment.utils.extensions.showToast
import com.eraybulut.sanstech_assignment.utils.extensions.vibrateOnClick
import com.eraybulut.sanstech_assignment.utils.extensions.visible
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
        setSwipeToRefreshLayout()
        listenToScrollRecyclerView()
        sendGetMatchesRequest()
        setupMatchAdapter()
    }

    override fun observeEvents() {
        with(viewModel) {
            collect(matchesListUIState, ::handleMatchesListUIState)
            collect(isRefreshVisible, ::setRefreshVisible)
        }
    }

    override fun initializeListeners() {
        with(binding) {
            icSort.vibrateOnClick {
                showMatchesSortDialog()
            }

            icFilter.vibrateOnClick {
                showMatchesFilterDialog()
            }

            icRefresh.vibrateOnClick {
                requireContext().showToast(message = getString(R.string.renewed))
                viewModel.refreshMatchesList()
            }

            icScrollToTop.vibrateOnClick {
                rvMatches.goStartPositionSmooth()
            }
        }
    }

    private fun sendGetMatchesRequest() {
        viewModel.getMatches()
    }

    private fun setupMatchAdapter() {
        with(binding.rvMatches) {
            itemAnimator = null
            adapter = competitionHeaderAdapter

            competitionHeaderAdapter.setOnMatchClickListener { matchEventListener ->
                handleMatchClickEvent(matchEventListener = matchEventListener)
            }
        }
    }

    private fun handleMatchClickEvent(matchEventListener: MatchEventListener) {
        when (matchEventListener) {
            is MatchEventListener.OnFavoritesClicked -> changeFavoriteStatus(matchId = matchEventListener.matchId)
            is MatchEventListener.OnMatchClicked -> Unit //Todo GoTo Detail page
        }
    }

    private fun changeFavoriteStatus(matchId: Int) {
        viewModel.toggleFavoriteStatus(matchId)
    }

    private fun listenToScrollRecyclerView() {
        with(binding) {
            rvMatches.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && icScrollToTop.visibility == View.GONE) {
                        icScrollToTop.fadeIn()
                    } else if (dy < 0 && icScrollToTop.visibility == View.VISIBLE) {
                        icScrollToTop.fadeOut()
                    }
                }
            })
        }
    }

    private fun handleMatchesListUIState(uiState: MatchesListUIState) {
        when (uiState) {
            MatchesListUIState.Empty -> showEmptyUI()
            MatchesListUIState.Idle -> Unit
            is MatchesListUIState.Success -> setMatchesList(matches = uiState.data)
        }
    }

    private fun setMatchesList(matches: List<LeagueMatchesUIModel>) {
        showSuccessUI()
        competitionHeaderAdapter.submitList(matches)
        binding.rvMatches.goStartPosition()
    }

    private fun setSwipeToRefreshLayout() {
        with(binding.swipeRefreshLayout) {
            setProgressBackgroundColorSchemeResource(R.color.black_pearly)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener {
                binding.rvMatches.goStartPosition()
                viewModel.refreshMatchesList()
            }
        }
    }

    private fun setRefreshVisible(isVisible: Boolean) {
        with(binding.swipeRefreshLayout) {
            isRefreshing = isVisible
        }
    }

    private fun showSuccessUI() {
        with(binding) {
            rvMatches.visible()
            llNoShowingMatches.gone()
        }
    }

    private fun showEmptyUI() {
        with(binding) {
            rvMatches.gone()
            llNoShowingMatches.visible()
        }
    }

    private fun showMatchesFilterDialog() {
        MatchesFilterBottomSheet().apply {
            setOnFilterClickListener { types ->
                resetAndFilterMatches(matchStatus = types)
            }
        }.show(childFragmentManager, "customFilterBottomSheet")
    }

    private fun resetAndFilterMatches(matchStatus: MatchStatusTypes) {
        viewModel.filterMatchesByStatus(status = matchStatus)
    }

    private fun showMatchesSortDialog() {
        MatchesSortBottomSheet().apply {
            setOnSortClickListener {
                sortMatches(sortTypes = it)
            }
        }.show(childFragmentManager, "sortMatchDialog")
    }

    private fun sortMatches(sortTypes: SortOrderTypes) {
        viewModel.sortMatches(sortOrder = sortTypes)
    }
}
