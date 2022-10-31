package com.loktarius.presentation

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loktarius.domain.model.Activity
import com.loktarius.domain.model.Tag
import com.loktarius.presentation.activities.ActivitiesEvent
import com.loktarius.presentation.activities.HomeScreenActivityViewModel
import com.loktarius.presentation.activities.components.ActivityItem
import com.loktarius.presentation.tags.HomeScreenTagsViewModel
import com.loktarius.presentation.tags.TagsEvent
import com.loktarius.presentation.tags.components.TagItem
import com.loktarius.presentation.timers.HomeScreenTimingState
import com.loktarius.presentation.timers.HomeScreenTimingViewModel
import com.loktarius.presentation.timers.TimingEvent
import com.loktarius.presentation.timers.stopwatch.StopwatchViewModel
import com.loktarius.presentation.timers.stopwatch.Stopwatch
import com.loktarius.presentation.util.Screen
import com.loktarius.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalFoundationApi::class, ExperimentalTime::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenTagsViewModel: HomeScreenTagsViewModel = hiltViewModel(),
    homeScreenActivityViewModel: HomeScreenActivityViewModel = hiltViewModel(),
    homeScreenTimingViewModel: HomeScreenTimingViewModel = hiltViewModel(),
    stopwatchViewModel: StopwatchViewModel = hiltViewModel(),
    //timerViewModel TODO


) {
    val tagsState = homeScreenTagsViewModel.state.value
    val activityState = homeScreenActivityViewModel.state.value
    val timingState = homeScreenTimingViewModel.state.value

    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Choose your timing mode")
            },

            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { homeScreenTimingViewModel.onEvent(TimingEvent.ChangeTiming("Stopwatch")) ; openDialog.value = false},
                    ) {
                        Text("Stopwatch")
                    }
                    Button(
                        onClick = { homeScreenTimingViewModel.onEvent(TimingEvent.ChangeTiming("Timer")) ; openDialog.value = false },
                    ) {
                        Text("Timer")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
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
                Column {


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
                                        homeScreenTagsViewModel.onEvent(TagsEvent.ChooseTag(tag))
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

            Button(
                onClick = { openDialog.value = true }
            ) {
                Text("Mode")
            }
            if (timingState.stopwatch) {
                    Stopwatch(stopwatchViewModel, homeScreenTagsViewModel)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.background(Color.DarkGray, RoundedCornerShape(50))
                ) {
                    if (stopwatchViewModel.isPlaying) {
                        IconButton(onClick = {
                            saveTimeAndCreateActivity(
                                stopwatchViewModel,
                                homeScreenTagsViewModel,
                                homeScreenActivityViewModel
                            )
                        }) {
                            Icon(imageVector = Icons.Filled.Save, contentDescription = "")

                        }
                    } else {
                        IconButton(onClick = { stopwatchViewModel.start() }) {
                            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
                        }
                    }
                    IconButton(onClick = { stopwatchViewModel.stop() }) {
                        Icon(imageVector = Icons.Filled.Stop, contentDescription = "")
                    }

                }
            } else if (timingState.timer) {

            }
            Spacer(Modifier.height(10.dp))


            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .width(50.dp)
                .height(50.dp)) {
                        items(activityState.activities) { activity ->
                            ActivityItem(activity = activity)
                }
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

@OptIn(ExperimentalTime::class)
@Composable
fun Stopwatch(
    stopwatchViewModel: StopwatchViewModel,
    tagsViewModel: HomeScreenTagsViewModel
) {
    Stopwatch(isPlaying = stopwatchViewModel.isPlaying,
        seconds = stopwatchViewModel.seconds,
        minutes = stopwatchViewModel.minutes,
        hours = stopwatchViewModel.hours,
        onStart = {stopwatchViewModel.start()},
        onSave = { tagsViewModel.state.value.lastUsedTag?.let { stopwatchViewModel.save(it) } },
        onStop = {stopwatchViewModel.stop()},
    )
}

@OptIn(ExperimentalTime::class)
fun saveTimeAndCreateActivity(
    stopwatchViewModel: StopwatchViewModel,
    tagsViewModel: HomeScreenTagsViewModel,
    activityViewModel: HomeScreenActivityViewModel,
) {
    var tag: Tag? = tagsViewModel.state.value.lastUsedTag
    var activity: Activity = if (tag != null ) {
        stopwatchViewModel.save(tag)
    } else {
        //negative values for debugging
        Activity(
            id = -2,
            tagId = -2,
            startingTime = 0,
            endingTime = 0
        )
    }
    Log.d("HOMESCREEN SAVE",activity.toString())
    activityViewModel.onEvent(ActivitiesEvent.CreateActivity(activity))
}






