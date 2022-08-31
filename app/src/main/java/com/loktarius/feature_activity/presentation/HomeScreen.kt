package com.loktarius.feature_activity.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import kotlinx.coroutines.launch


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
                Text(text = "Hello from sheet")
                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(state.tags) { tag ->
                        TagItem(
                            tag = tag,
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .clickable {

                                },

                        )
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                }
            }
        }, sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Green    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
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

