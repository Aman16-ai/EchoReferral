package com.example.echoreferral.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MDivider(modifier: Modifier=Modifier) {
    Divider (
        color = Color(0xFFF0F2F5),
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()

    )
}