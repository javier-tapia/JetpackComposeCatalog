package com.cursokotlin.jetpackcomponentscatalog.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedContentExample(paddingValues: PaddingValues) {
    var number by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { number++ }) {
            Text("Sumar")
        }

        Spacer(Modifier.height(32.dp))

        AnimatedContent(targetState = number) { result ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("El valor es: $result")

                when (result) {
                    0 -> Box(
                        Modifier
                            .size(50.dp)
                            .background(Color.Red)
                    )
                    1 -> FloatingActionButton({}) { }
                    2 -> Text("Something")
                    else -> Icon(imageVector = Icons.Default.Home, contentDescription = "")
                }
            }
        }
    }
}

@Composable
fun AnimateContentSizeExample() {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .animateContentSize()
            .height(if (isExpanded) 400.dp else 200.dp)
            .clickable { isExpanded = !isExpanded },
        contentAlignment = Alignment.Center
    ) {
        Text("Helloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
    }
}
