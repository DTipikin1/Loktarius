package com.loktarius.feature_activity.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loktarius.feature_activity.presentation.activities.ActivitiesViewModel
import com.loktarius.feature_activity.presentation.activities.components.TagItem
import com.loktarius.feature_activity.presentation.util.Screen
import com.loktarius.ui.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ActivitiesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
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

                    items(state.tags) { tag ->
                        TagItem(
                            tag = tag,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .combinedClickable(
                                    onClick = { },
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
        sheetBackgroundColor = RedPink) {
        Column(
            modifier = Modifier
                .background(RedOrange)
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

