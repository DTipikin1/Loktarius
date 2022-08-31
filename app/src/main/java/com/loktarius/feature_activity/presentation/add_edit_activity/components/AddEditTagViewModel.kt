package com.loktarius.feature_activity.presentation.add_edit_activity.components

import androidx.lifecycle.ViewModel
import com.loktarius.feature_activity.domain.use_case.TagUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTagViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {

}