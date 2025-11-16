package com.cursokotlin.jetpackcomponentscatalog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cursokotlin.jetpackcomponentscatalog.animations.InfiniteTransitionExample
import com.cursokotlin.jetpackcomponentscatalog.ui.theme.JetpackComponentsCatalogTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            JetpackComponentsCatalogTheme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            modifier = Modifier
                                .padding(bottom = 16.dp),
                            hostState = snackbarHostState,
                        ) {
                            CustomSnackbar(snackbarHostState.currentSnackbarData?.visuals?.message)
                        }
                    },
                    content = { paddingValues ->
                        InfiniteTransitionExample(paddingValues)
                    }
                )
            }
        }
    }
}
