package com.eraybulut.sanstech_assignment.ui.matcheslist

import com.eraybulut.sanstech_assignment.base.BaseViewModel
import com.eraybulut.sanstech_assignment.domain.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

@HiltViewModel
class MatchesListViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

}