package com.example.firstcomposeproject.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun InstagramProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(width = 1.dp, color = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Box( modifier = Modifier
                .size(50.dp)
                .background(color = Color.Yellow),
                contentAlignment = Alignment.Center
            ) {

            }

            TwoBoxes("6.950", "Posts")
            TwoBoxes("436M", "Followers")
            TwoBoxes("76", "Following")
        }

    }
}

@Composable
fun TwoBoxes(count: String, type: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box { Text(text = "$count") }
        Box { Text(text = "$type") }
    }
}

