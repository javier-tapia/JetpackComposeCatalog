package com.cursokotlin.jetpackcomponentscatalog

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.jetpackcomponentscatalog.model.Superhero
import com.cursokotlin.jetpackcomponentscatalog.utils.ItemHero
import com.cursokotlin.jetpackcomponentscatalog.utils.getSuperheroes
import kotlinx.coroutines.launch

/**
 * Para crear listas dinámicas se usan las funciones ``Composables`` ``LazyColumn`` (vertical) o ``LazyRow`` (horizontal).
 *
 * Para añadir elementos a estas listas, se utilizan las funciones ``item`` o ``items`` (para más de un elemento).
 *
 * ``LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {...}``:
 * La función ``spacedBy`` permite agregar un espacio entre las vistas hijas del componente
 * a través del eje principal (vertical para ``LazyColumn``, horizontal para ``LazyRow``).
 * Si el espacio es negativo, los hijos van a superponerse.
 *
 * La función ``rememberLazyListState`` crea y recuerda el estado de la lista a través de las diferentes composiciones.
 *
 * ***Sticky headers*** **(cabeceras)**: La función ``stickyHeader`` permite agregar una cabecera que permanece fija
 * hasta que la próxima cabecera ocupe su lugar.
 */
@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Aris", "Pepe", "Manolo", "Jaime")
    LazyColumn {
        item { Text(text = "Header") }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero)
            { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
        }
    }
}

@Composable
fun SuperHeroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState, verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
            }
        }

        val showbutton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        remember { derivedStateOf { rvState.firstVisibleItemScrollOffset } }

        if (showbutton) {

            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Soy un boton cool")
            }
        }
    }


}

@ExperimentalFoundationApi
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<Char, List<Superhero>> = getSuperheroes().groupBy { it.realName[0] }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (initial, mySuperHero) ->

            stickyHeader {
                Text(
                    text = initial.toString(), modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green), fontSize = 16.sp, color = Color.White
                )
            }

            items(mySuperHero) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
            }
        }
    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    val data = getSuperheroes()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(data.size) { superhero ->
                ItemHero(superhero = data[superhero])
                { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
            }
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun MyAdvanceList(modifier: Modifier = Modifier) {
    var items by rememberSaveable { mutableStateOf(List(100) { "Item número $it" }) }

    LazyColumn {
        itemsIndexed(items = items, key = { _, item -> item }) { index, item ->
            Row {
                Text("$item and index: $index")
                Spacer(Modifier.weight(1f))
                TextButton(
                    onClick = {
                        items = items.toMutableList().apply {
                            remove(item)
                        }
                    }
                ) {
                    Text("Borrar")
                }
                Spacer(Modifier.width(24.dp))
            }
        }
    }
}

@Composable
fun MyScrollList(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 5
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            state = listState
        ) {
            items(100) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Item: $it"
                )
            }
        }

        if (showButton) {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Ir al inicio")
            }
        }
    }
}
