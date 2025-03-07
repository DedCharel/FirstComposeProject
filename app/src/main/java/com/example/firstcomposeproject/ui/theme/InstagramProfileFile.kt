package com.example.firstcomposeproject.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.MainViewModel
import com.example.firstcomposeproject.R


@Composable
fun InstagramProfileCard(
    viewModel:MainViewModel
) {
    val isFlowed = viewModel.isFollowing.observeAsState(false)

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_instagram),
                contentDescription = ""
            )

            UserStatistics("Posts", "6.950")
            UserStatistics("Followers", "436M")
            UserStatistics("Following", "76")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Instagram",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold)
            Text(text = "#YoursToMake",
                fontSize = 14.sp)
            Text(text = "www.facebook.com/emotional_health",
                fontSize = 14.sp)
            FollowButton(isFlowed) {
                viewModel.changeFollowingStatus()
            }
        }
    }
}

@Composable
fun FollowButton(
    isFollowed: State<Boolean>,
    clickListener: () -> Unit
){
    Button(
        shape = RoundedCornerShape(8.dp),
        onClick = { clickListener()},
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed.value){
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    )
     {
        val text = if (isFollowed.value){
            "Unfollow"

        } else {
            "Follow"
        }
        Text(text = text)
    }
}

@Composable
fun UserStatistics(title: String, value: String) {

    Column(
        modifier = Modifier.height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
    }
}



