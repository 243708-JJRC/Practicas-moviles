package com.jjuanrc.examen_u3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jjuanrc.examen_u3.screens.DetailScreen
import com.jjuanrc.examen_u3.screens.FavoritesScreen
import com.jjuanrc.examen_u3.screens.HomeScreen
import com.jjuanrc.examen_u3.screens.SearchScreen
import com.jjuanrc.examen_u3.viewmodel.PokemonViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{pokemonId}/{pokemonName}") {
        fun createRoute(id: Int, name: String) = "detail/$id/$name"
    }
    object Search : Screen("search")
    object Favorites : Screen("favorites")
}

@Composable
fun PokemonNavigation(viewModel: PokemonViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onPokemonClick = { id, name ->
                    navController.navigate(Screen.Detail.createRoute(id, name))
                },
                onSearchClick = {
                    navController.navigate(Screen.Search.route)
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.IntType },
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 0
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""

            DetailScreen(
                viewModel = viewModel,
                pokemonId = pokemonId,
                pokemonName = pokemonName,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onPokemonClick = { id, name ->
                    navController.navigate(Screen.Detail.createRoute(id, name))
                }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onPokemonClick = { id, name ->
                    navController.navigate(Screen.Detail.createRoute(id, name))
                }
            )
        }
    }
}
