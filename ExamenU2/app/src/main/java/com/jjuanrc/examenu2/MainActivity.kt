package com.jjuanrc.examenu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjuanrc.examenu2.screens.DashboardScreen
import com.jjuanrc.examenu2.screens.ThemeScreen
import com.jjuanrc.examenu2.screens.UserScreen
import com.jjuanrc.examenu2.viewmodel.ThemeViewModel
import com.jjuanrc.examenu2.viewmodel.UserViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            val isDark by themeViewModel.isDarkMode.collectAsState(initial = false)
            val navController = rememberNavController()
            AppTheme(isDark) {
                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") { DashboardScreen(navController) }
                    composable("theme") { ThemeScreen(themeViewModel, navController) }
                    composable("user") { UserScreen(userViewModel, navController) }
                }
            }
        }
    }
}
@Composable
fun AppTheme(isDark: Boolean, content: @Composable () -> Unit) {
    val darkColors = darkColorScheme()
    val lightColors = lightColorScheme()

    MaterialTheme(
        colorScheme = if (isDark) darkColors else lightColors
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}