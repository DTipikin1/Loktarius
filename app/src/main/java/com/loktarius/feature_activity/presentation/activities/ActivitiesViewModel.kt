package com.loktarius.feature_activity.presentation.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.use_case.TagUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TagsState())
    val state: State<TagsState> = _state

    private var recentlyDeletedTag: Tag? = null

    fun onEvent(event: TagsEvent) {
        when (event) {
            is TagsEvent.Order -> {

            }
            is TagsEvent.DeleteTag -> {
                viewModelScope.launch {
                    tagUseCases.deleteTag(event.tag)
                    recentlyDeletedTag = event.tag
                }

            }
            is TagsEvent.RestoreTag -> {


            }

        }
    }
}