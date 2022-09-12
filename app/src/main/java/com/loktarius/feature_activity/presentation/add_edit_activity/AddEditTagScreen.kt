package com.loktarius.feature_activity.presentation.add_edit_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loktarius.feature_activity.presentation.add_edit_activity.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTagScreen(
    navController: NavController,
    viewModel: AddEditTagViewModel = hiltViewModel()
) {
    val nameState = viewModel.tagName.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTagViewModel.UiEvent.showSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )

                }
                is AddEditTagViewModel.UiEvent.SaveTag -> {
                    navController.navigateUp()
                }
            }
        }
    }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTagEvent.SaveTag)
            },
                backgroundColor = MaterialTheme.colors.primary

            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save tag")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                onValueChange = {
                                viewModel.onEvent(AddEditTagEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTagEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
        }
    }

}
