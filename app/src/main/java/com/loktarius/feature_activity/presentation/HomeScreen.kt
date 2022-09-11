package com.loktarius.feature_activity.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loktarius.feature_activity.presentation.activities.HomeScreenTagsViewModel
import com.loktarius.feature_activity.presentation.activities.TagsEvent
import com.loktarius.feature_activity.presentation.activities.components.TagItem
import com.loktarius.feature_activity.presentation.util.Screen
import com.loktarius.ui.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenTagsViewModel = hiltViewModel()
) {
    val tagsState = viewModel.state.value
    var lastUsedTagHS by remember {
        mutableStateOf(tagsState.lastUsedTag)
    }


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Column() {


                Text(text = "Long click on tag to edit it, quick click to chose it")
                AddTagButton(navController = navController)
                LazyRow(modifier = Modifier.fillMaxWidth()) {

                    items(tagsState.tags) { tag ->
                        TagItem(
                            tag = tag,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .combinedClickable(
                                    onClick = {
                                        viewModel.onEvent(TagsEvent.ChooseTag(tag))
                                    },
                                    onLongClick = {
                                        navController.navigate(
                                            Screen.AddEditTagScreen.route
                                                    + "?tagId=${tag.id}"
                                        )
                                    },
                                )

                        )
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                }
            }
            }
        }, sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Blue) {
        Column(
            modifier = Modifier
                .background(BabyBlue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Button(onClick = {
                coroutineScope.launch {

                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
                Text(text = "Tag")

            }
            Text(text = tagsState.lastUsedTag.toString())

            Spacer(modifier = Modifier.padding(50.dp))
            Text("00:00:00",
            )
            Button(onClick = {/*TODO*/}) {
                Text("Start")
            }

        }

        }
}



@Composable
fun AddTagButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(Screen.AddEditTagScreen.route)
    }) {
        Text(text = "Add Tag")
    }
}




