package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import com.example.firstcomposeproject.ui.theme.InstagramProfileCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            Test(viewModel = viewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Test(viewModel: MainViewModel){
    FirstComposeProjectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            val models = viewModel.models.observeAsState(listOf())
            LazyColumn {
                items(models.value, key = {it.id}){ model ->
                    val dismissThresholds = with(receiver = LocalDensity.current) {
                        LocalConfiguration.current.screenWidthDp.dp.toPx() * 0.5F
                    }

                    val dismissState = SwipeToDismissBoxState(
                        positionalThreshold = {dismissThresholds},
                        initialValue = SwipeToDismissBoxValue.Settled,
                        density = LocalDensity.current,
                        confirmValueChange = {value ->
                            val isDismissed = value in setOf(
                                SwipeToDismissBoxValue.EndToStart,
                                )
                            if (isDismissed){
                                viewModel.delete(model)
                                true
                            }
                            false

                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                                    .background(Color.Red.copy(alpha = 0.7F)),
                                contentAlignment = Alignment.CenterEnd){
                                
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Delete Item",
                                    color = Color.White,
                                    fontSize = 14.sp)
                            }
                        }
                    )
                    {
                        InstagramProfileCard(
                            model = model,
                            onFollowedButtonClickListener = {
                                viewModel.changeFollowingStatus(it)
                            }
                        )
                    }

                }

            }

        }

    }
}







