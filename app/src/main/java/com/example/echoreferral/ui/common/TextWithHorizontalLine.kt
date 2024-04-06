package com.example.echoreferral.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithHoriLine(text:String,modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,modifier = modifier) {
        Text(text, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        MDivider(modifier= Modifier.padding(start = 5.dp, end = 5.dp))
    }
}