package com.loktarius.feature_activity.presentation.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.use_case.TagUseCases
import com.loktarius.feature_activity.domain.util.OrderType
import com.loktarius.feature_activity.domain.util.TagOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TagsState())
    val state: State<TagsState> = _state

    private var recentlyDeletedTag: Tag? = null

    private var getTagsJob: Job? = null

    init {
        getTags(TagOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TagsEvent) {
        when (event) {
            is TagsEvent.Order -> {
                if (state.value.tagOrder::class == event.tagOrder::class &&
                        state.value.tagOrder.orderType == event.tagOrder.orderType) {
                    return
                }
                getTags(event.tagOrder)
            }
            is TagsEvent.DeleteTag -> {
                viewModelScope.launch {
                    tagUseCases.deleteTag(event.tag)
                    recentlyDeletedTag = event.tag
                }
            }
            is TagsEvent.RestoreTag -> {
                viewModelScope.launch {
                    tagUseCases.addTag(recentlyDeletedTag ?: return@launch)
                    recentlyDeletedTag = null
                }
            }

        }
    }
    private fun getTags(tagOrder: TagOrder) {
        tagUseCases.getTags(tagOrder)
            .onEach { tags ->
                _state.value = state.value.copy(
                    tags = tags,
                    tagOrder = tagOrder
                    )
            }
            .launchIn(viewModelScope)
    }
}