package ortiz.derek.c4.cuartitoscapp.Details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.jjuanrc.cuartoscapp.Data.Students

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homeview (navControler: NavController){
    val  student = ListaEstudiantes()
    Scaffold (
        topBar ={
            CenterAlignedTopAppBar(
                title ={
                    Text("Dashboard")
                }
            )
        }
    ){
        Content(it, student, navControler)
    }
}

@Composable
fun Content(paddingValues: PaddingValues, students: List<Students>, navController: NavController ){
    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)

    ){
        items(students){
                student ->
            Box(
                modifier = Modifier
                    .clickable {
                        navController.navigate("Detalis/${student.id}")

                    }

                    .clip(RoundedCornerShape(10))
//                    .border(width = 2.dp, color = Color(0xFF0033FF))
                    .fillMaxWidth()
                    .size(
                        height = 200.dp,
                        width = 100.dp
                    )
                    .background(color = Color(0xFF11307D))
                    .wrapContentSize(Alignment.Center)
            ) {
                Column {
                    Text(student.name, color = Color.White)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(student.image)
                            .transformations(CircleCropTransformation())
                            .build(),
                        contentDescription = "Imagen de ${student.description}"
                    )


                }

            }
            Spacer(modifier = Modifier.height((10.dp)))
        }
    }
}
@Composable
fun ListaEstudiantes(): List<Students> {
    var student = listOf<Students>(
        Students(1, "Leonardo", "El que organiza los equipos de fútbol en el recreo.", "https://picsum.photos/250"),
        Students(2, "Daniela", "La que siempre tiene un cargador de celular para prestar.", "https://picsum.photos/250"),
        Students(3, "Adrián", "El que pregunta '¿puedo poner música?' en los trabajos en equipo.", "https://picsum.photos/250"),
        Students(4, "Fernanda", "La que subraya todo el libro con marcadores de colores.", "https://picsum.photos/250"),
        Students(5, "Ricardo", "El que siempre tiene un chiste malo listo para cualquier ocasión.", "https://picsum.photos/250"),
        Students(6, "Valentina", "La que sabe todos los cumpleaños y organiza los festejos.", "https://picsum.photos/250"),
        Students(7, "Carlos", "El que hace malabares con el lápiz mientras el profesor explica.", "https://picsum.photos/250"),
        Students(8, "Ximena", "La que siempre trae galletas o papitas para compartir.", "https://picsum.photos/250"),
        Students(9, "Andrés", "El que responde los mensajes del grupo de clase a las 3 a.m.", "https://picsum.photos/250"),
        Students(10, "Mariana", "La que pide que repitan la explicación 'pero más despacio'.", "https://picsum.photos/250"),
        Students(11, "Emiliano", "El que siempre olvida la cartulina el día de la exposición.", "https://picsum.photos/250"),
        Students(12, "Renata", "La que tiene el estuche con más plumas y plumones que una papelería.", "https://picsum.photos/250"),
        Students(13, "Joaquín", "El experto en tecnología que arregla el proyector del salón.", "https://picsum.photos/250"),
        Students(14, "Luciana", "La que siempre tiene una excusa creativa para no entregar la tarea.", "https://picsum.photos/250"),
        Students(15, "Miguel", "El que sabe la respuesta correcta pero nunca levanta la mano.", "https://picsum.photos/250"),
        Students(16, "Ana", "La que pregunta si el trabajo es en parejas apenas lo asignan.", "https://picsum.photos/250")
    )
    return student
}