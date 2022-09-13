package com.loktarius.feature_activity.presentation.timers.stopwatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.loktarius.feature_activity.domain.model.Tag

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
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.background(Color.DarkGray, RoundedCornerShape(50))
            ) {
                if (isPlaying) {
                    IconButton(onClick = onSave) {
                        Icon(imageVector = Icons.Filled.Save, contentDescription = "")
                    }
                }
                else {
                    IconButton(onClick = onStart) {
                        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
                    }
                }
                IconButton(onClick = onStop) {
                    Icon(imageVector = Icons.Filled.Stop, contentDescription = "")
                }

            }
            Spacer(Modifier.height(10.dp))

        }
    //}

}