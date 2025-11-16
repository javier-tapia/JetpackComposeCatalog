package com.cursokotlin.jetpackcomponentscatalog

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.cursokotlin.jetpackcomponentscatalog.utils.MyDrawer
import kotlinx.coroutines.launch

/**
 * Sobre ``TopAppBar`` y ``Scaffold``,
 * ver [acá](https://github.com/javier-tapia/Apuntes-y-Navaja-Suiza/blob/master/Android/UI/Jetpack%20Compose.md#slot-api--scaffold)
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ModalNavigationDrawerWithScaffold() {
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = { MyDrawer { coroutineScope.launch { drawerState.close() } } }
            )
        },
        gesturesEnabled = false,
        content = {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                content = { paddingValues ->
                    Log.d("paddingValues", "$paddingValues")
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .consumeWindowInsets(paddingValues)
                    ) {
                        Text(text = "Text 1")
                        Text(text = "Text 2")
                    }
                },
                topBar = {
                    MyTopAppBar(
                        onClickIcon = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    "Has pulsado $it"
                                )
                            }
                        },
                        onClickDrawer = { coroutineScope.launch { drawerState.open() } })
                },
                bottomBar = { MyBottomNavigation() },
                floatingActionButton = { MyFAB() }, // En MD3, el FAB ya no se puede anclar (dock) a la NavigationBar
                floatingActionButtonPosition = FabPosition.End
            )
        }
    )
}
