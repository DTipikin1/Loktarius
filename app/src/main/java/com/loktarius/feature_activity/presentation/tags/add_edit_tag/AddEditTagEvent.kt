package com.loktarius.feature_activity.presentation.tags.add_edit_tag

import androidx.compose.ui.focus.FocusState

sealed class AddEditTagEvent {

    data class EnteredName(val value: String): AddEditTagEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditTagEvent()
    object SaveTag: AddEditTagEvent()
}


