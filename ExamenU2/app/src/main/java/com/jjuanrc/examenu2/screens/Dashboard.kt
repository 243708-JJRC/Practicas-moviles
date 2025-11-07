package com.jjuanrc.examenu2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(onClick = { navController.navigate("theme") }) {
            Text("Cambiar tema")
        }
        Button(onClick = { navController.navigate("user") }) {
            Text("Guardar usuario")
        }
    }
}