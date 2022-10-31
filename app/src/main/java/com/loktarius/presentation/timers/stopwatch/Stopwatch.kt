package com.loktarius.presentation.timers.stopwatch

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Stopwatch(
    isPlaying: Boolean,
    seconds: String,
    minutes: String,
    hours: String,
    onStart: () -> Unit = {},
    onSave: () -> Unit = {},
    onStop: () -> Unit = {},

) {
    //Scaffold {
        Column(Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            //Spacer(Modifier.weight(1f))
            Row {
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.h3) {
                    Text(text = hours)
                    Text(text = ":")
                    Text(text = minutes)
                    Text(text = ":")
                    Text(text = seconds)
                }

            }
            //Spacer(Modifier.weight(1f))


        }
    //}

}