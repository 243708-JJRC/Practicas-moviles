package com.jjuanrc.examenu2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjuanrc.examenu2.viewmodel.ThemeViewModel


@Composable
fun ThemeScreen(themeViewModel: ThemeViewModel, navController: NavController) {
    val isDark by themeViewModel.isDarkMode.collectAsState(initial = false)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Modo oscuro:")
        Switch(
            checked = isDark,
            onCheckedChange = { themeViewModel.toggleTheme(it) }
        )

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}