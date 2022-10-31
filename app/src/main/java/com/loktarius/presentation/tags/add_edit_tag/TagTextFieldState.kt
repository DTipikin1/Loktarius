package com.loktarius.presentation.tags.add_edit_tag

data class TagTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
