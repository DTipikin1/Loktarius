package com.loktarius.presentation.activities.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.loktarius.domain.model.Activity
import com.loktarius.ui.theme.DarkGray

@Composable
fun ActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
) {
    Box(
        modifier = modifier
            .background(DarkGray),
    ) {
        Text(
            text = activity.id.toString(),
            overflow = TextOverflow.Ellipsis
        )
    }

}