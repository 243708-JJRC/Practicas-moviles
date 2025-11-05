package ortiz.derek.c4.cuartitoscapp.Details.navManager

import androidx.compose.runtime.Composable

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ortiz.derek.c4.cuartitoscapp.Details.view.DetalisView
import ortiz.derek.c4.cuartitoscapp.Details.view.Homeview
import ortiz.derek.c4.cuartitoscapp.Details.view.ListaEstudiantes


@Composable
fun Navmanager(){
    val students = ListaEstudiantes()
    val navController = rememberNavController()


    NavHost(
        navController,
        startDestination = "Home"
    ){
        composable ("Home"){
            Homeview(navController)
        }
        composable ("Detalis/{id}", arguments =  listOf(

            navArgument(name= "id"){
                type = NavType.IntType
            }
        )) { it ->
            val id = it.arguments?.getInt("id")?:0
            DetalisView(navController,id, students)
        }


    }
}

