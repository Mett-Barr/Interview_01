package com.example.interview01.topic.launch_mode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.interview01.topic.launch_mode.activitys.SingleInstanceActivity
import com.example.interview01.topic.launch_mode.activitys.SingleTaskActivity
import com.example.interview01.topic.launch_mode.activitys.SingleTopActivity
import com.example.interview01.topic.launch_mode.activitys.StandardActivity
import com.example.interview01.ui.theme.Interview01Theme
import com.example.interview01.util.enumName

class LaunchModeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interview01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchModePage()
                }
            }
        }
    }
}

@Composable
fun LaunchModePage() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Launch Mode",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(LaunchMode.entries) {
                Card(modifier = Modifier
                    .height(80.dp)
                    .clickable {
                        context.startActivity(Intent(context, it.activity))
                    }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = it.name.enumName(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

enum class LaunchMode(val activity: Class<out Activity>) {
    STANDARD(StandardActivity::class.java),
    SINGLE_TOP(SingleTopActivity::class.java),
    SINGLE_TASK(SingleTaskActivity::class.java),
    SINGLE_INSTANCE(SingleInstanceActivity::class.java)
}
