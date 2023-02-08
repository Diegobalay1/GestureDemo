package com.example.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gesturedemo.ui.theme.GestureDemoTheme
import androidx.compose.ui.unit.IntOffset

import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestureDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    //ClickDemo()
    //TapPressDemo()
    DragDemo()
}

@Composable
fun DragDemo() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember { mutableStateOf(0f) }

        Box(modifier = Modifier
            .offset { IntOffset(x = xOffset.roundToInt(), y = 0) }
            .size(100.dp)
            .background(Color.Blue)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { distance ->
                    xOffset += distance
                }
            )
        )
    }
}

@Composable
fun TapPressDemo() {
    var textState by remember {
        mutableStateOf("Waiting ....")
    }

    val tapHandler = { status: String ->
        textState = status
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .padding(10.dp)
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { tapHandler("onPress Detected") },
                        onDoubleTap = { tapHandler("onDoubleTap Detected") },
                        onLongPress = { tapHandler("onLongPress Detected") },
                        onTap = { tapHandler("onTap Detected") }
                    )
                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = textState)
    }
}

@Composable
fun ClickDemo() {
    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = {
        colorState = !colorState
        bgColor = if (colorState) {
            Color.Blue
        } else {
            Color.DarkGray
        }
    }
    Box(modifier = Modifier
        .clickable { clickHandler() }
        .background(bgColor)
        .size(100.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GestureDemoTheme {
        MainScreen()
    }
}


















