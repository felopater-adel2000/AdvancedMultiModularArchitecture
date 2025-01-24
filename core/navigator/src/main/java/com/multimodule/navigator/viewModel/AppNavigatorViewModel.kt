package com.multimodule.navigator.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptionsBuilder
import com.multimodule.navigator.core.AppNavigator
import com.multimodule.navigator.event.NavigatorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



@HiltViewModel
class AppNavigatorViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), AppNavigator by appNavigator
{

}