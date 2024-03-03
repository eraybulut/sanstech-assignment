package com.eraybulut.sanstech_assignment.ui.matchdetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eraybulut.sanstech_assignment.R
import com.eraybulut.sanstech_assignment.base.BaseFragment
import com.eraybulut.sanstech_assignment.databinding.FragmentMatchDetailsBinding
import com.eraybulut.sanstech_assignment.ui.matcheslist.MatchesItemUIModel
import com.eraybulut.sanstech_assignment.utils.Constants.MATCH_KEY
import com.eraybulut.sanstech_assignment.utils.extensions.copyToClipboard
import com.eraybulut.sanstech_assignment.utils.extensions.orFalse
import com.eraybulut.sanstech_assignment.utils.extensions.parcelable
import com.eraybulut.sanstech_assignment.utils.extensions.shareText
import com.eraybulut.sanstech_assignment.utils.extensions.vibrateOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsFragment : BaseFragment<FragmentMatchDetailsBinding, MatchDetailsViewModel>(
    FragmentMatchDetailsBinding::inflate
) {

    private val matchModel by lazy { arguments?.parcelable<MatchesItemUIModel>(MATCH_KEY) }

    override val viewModel: MatchDetailsViewModel by viewModels()

    override fun onCreateFinished() {
        setupUI()
    }

    override fun initializeListeners() {
        with(binding) {
            icBack.vibrateOnClick {
                findNavController().popBackStack()
            }

            icCopy.vibrateOnClick {
                requireContext().copyToClipboard(text = "${matchModel?.homeTeamName} ${matchModel?.matchScore} ${matchModel?.awayTeamName}")
            }

            icShare.vibrateOnClick {
                requireContext().shareText(message = "${matchModel?.homeTeamName} ${matchModel?.matchScore} ${matchModel?.awayTeamName}")
            }
        }
    }

    private fun setupUI() {
        with(binding) {
            txtHomeTeamName.text = matchModel?.homeTeamName.orEmpty()
            txtAwayTeamName.text = matchModel?.awayTeamName.orEmpty()
            txtScore.text = matchModel?.matchScore.orEmpty()
            txtMatchStatus.text = matchModel?.matchAbbr.orEmpty()
            val favoritesResource = if (matchModel?.isFavorite.orFalse()) {
                R.drawable.ic_star_fill
            } else {
                R.drawable.ic_star_outline
            }
            icStar.setImageResource(favoritesResource)
        }
    }
}