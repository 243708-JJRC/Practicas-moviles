package com.jjuanrc.examenu2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjuanrc.examenu2.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel, navController: NavController) {
    val users by viewModel.users.collectAsState()
    var name by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (name.isNotBlank() && edad.isNotBlank()) {
                viewModel.addUser(name, edad.toIntOrNull() ?: 0)
                name = ""
                edad = ""
            }
        }) {
            Text("Guardar usuario")
        }

        Spacer(Modifier.height(16.dp))
        Text("Usuarios guardados:")
        users.forEach { user ->
            Text("- ${user.name}, ${user.age} años")
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}