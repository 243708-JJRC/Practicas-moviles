package ortiz.derek.c4.cuartitoscapp.Details.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.jjuanrc.cuartoscapp.Data.Students


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalisView (navController: NavController, id: Int, students: List<Students>){
    Scaffold (
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                    Text("Details")

                },
                navigationIcon ={
                    IconButton(
                        onClick ={
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "regresar")
                    }
                }
            )
        }
    ){
        DetailsContent(it, id, students )
    }
}

@Composable
fun DetailsContent(PaddingValues: PaddingValues, id: Int, students: List<Students>){
    val findStudiente = students.find { it.id == id }
    Column (
        modifier = Modifier
            .padding(PaddingValues)
    ){
        Text("Hola ${findStudiente?.name}")
        Text("Eres el registro numero: ${id}")
        Text("Te describes de la siguiente manera:  ${findStudiente?.description}")
        Text("Tienes la siguiente imagen:   ${findStudiente?.image}")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(findStudiente?.image)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = "Imagen de ${findStudiente?.description}"
        )

    }
}

