package com.example.androidtodo.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodoDetail(modifier: Modifier = Modifier, title: String) {
    Text(text = title, modifier = modifier)
}