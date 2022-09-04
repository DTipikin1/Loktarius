package com.loktarius.feature_activity.presentation.add_edit_activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loktarius.feature_activity.domain.model.InvalidTagException
import com.loktarius.feature_activity.domain.model.Tag
import com.loktarius.feature_activity.domain.use_case.TagUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTagViewModel @Inject constructor(
    private val tagUseCases: TagUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _tagName = mutableStateOf(
        TagTextFieldState(
        hint = "Enter tag's name..."
    )
    )
    val tagName: State<TagTextFieldState> = _tagName

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTagId: Int? = null

    init {
        savedStateHandle.get<Int>("tagId")?.let { tagId ->
            if (tagId != -1) {
                viewModelScope.launch {
                    tagUseCases.getTag(tagId)?.also { tag ->
                        currentTagId = tag.id
                        _tagName.value = tagName.value.copy(
                            text = tag.name,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTagEvent) {
        when (event) {
            is AddEditTagEvent.EnteredName -> {
                _tagName.value = tagName.value.copy(
                    text = event.value
                )
            }
            is AddEditTagEvent.ChangeNameFocus -> {
                _tagName.value = tagName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            tagName.value.text.isBlank()
                )
            }
            is AddEditTagEvent.SaveTag -> {
                viewModelScope.launch {
                    try {
                        tagUseCases.addTag(
                            Tag(
                                name = tagName.value.text,
                                timestamp = System.currentTimeMillis(),
                                id = currentTagId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTag)
                    } catch(e: InvalidTagException) {
                        _eventFlow.emit(
                            UiEvent.showSnackbar(
                                message = e.message ?: "Couldn't save tag"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class showSnackbar(val message: String): UiEvent()
        object SaveTag: UiEvent()
    }
}