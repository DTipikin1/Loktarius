package com.loktarius.feature_activity.presentation.add_edit_activity

import androidx.compose.ui.focus.FocusState

sealed class AddEditTagEvent {

    data class EnteredName(val value: String): AddEditTagEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditTagEvent()
    object SaveTag: AddEditTagEvent()
}


