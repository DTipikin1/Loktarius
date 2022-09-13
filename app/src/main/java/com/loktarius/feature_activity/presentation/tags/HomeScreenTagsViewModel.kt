package com.loktarius.feature_activity.presentation.tags

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.use_case.tags.TagUseCases
import com.loktarius.feature_activity.domain.util.OrderType
import com.loktarius.feature_activity.domain.util.TagOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenTagsViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TagsState())
    val state: State<TagsState> = _state

    private var recentlyDeletedTag: Tag? = null

    private var getTagsJob: Job? = null
    private var getLastUsedTagJob: Job? = null


    init {
        getTags(TagOrder.Date(OrderType.Descending))
        getLastUsedTag()
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
            is TagsEvent.ChooseTag -> {
                var tag: Tag?
                viewModelScope.launch {
                    tag = event.tag.id?.let { tagUseCases.getTag(it) }
                    _state.value = state.value.copy(
                        lastUsedTag = tag?.id?.let { tagUseCases.getTag(it) }
                    )
                }
            }

        }
    }
    private fun getTags(tagOrder: TagOrder) {
        getTagsJob?.cancel()
        getTagsJob = tagUseCases.getTags(tagOrder)
            .onEach { tags ->
                _state.value = state.value.copy(
                    tags = tags,
                    tagOrder = tagOrder
                    )
            }
            .launchIn(viewModelScope)
    }
    private fun getLastUsedTag() {
        getLastUsedTagJob?.cancel()
            getLastUsedTagJob = tagUseCases.getLastUsedTag()
                .onEach { tag ->
                _state.value = state.value.copy(
                    lastUsedTag = tag
                )
            }.launchIn(viewModelScope)



    }
}