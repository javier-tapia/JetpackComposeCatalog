package com.cursokotlin.jetpackcomponentscatalog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Se le debe ***indicar un estado*** y la ***acción a realizar*** cuando el estado del componente cambia.
 */
@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {

        RadioButton(
            selected = false,
            onClick = { },
            enabled = false,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green
            )
        )

        Text(text = "Ejemplo 1")
    }
}
