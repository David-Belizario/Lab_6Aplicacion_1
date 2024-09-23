package com.androidcourse.laboratorio6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidcourse.laboratorio6.ui.theme.Laboratorio6Theme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio6Theme {
                AppNavigation()
            }
        }
    }
}

data class IconButtonData(val icon: ImageVector, val description: String, val route: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { MovilScreen2() }
        composable("build") { BuildScreen() }
        composable("menu") { MenuScreen() }
        composable("favorite") { FavoriteScreen() }
        composable("delete") { DeleteScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val clickCount = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB { clickCount.value++ } }
    ) { paddingValues ->
        CustomContent(padding = paddingValues, clickCount = clickCount.value)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* Acción para el botón de menú */ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Top bar ejemplo") },
        actions = {
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    val iconButtons = listOf(
        IconButtonData(Icons.Filled.Build, "Build description", "build"),
        IconButtonData(Icons.Filled.Menu, "Menu description", "menu"),
        IconButtonData(Icons.Filled.Favorite, "Favorite description", "favorite"),
        IconButtonData(Icons.Filled.Delete, "Delete description", "delete")
    )

    BottomAppBar {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
        ) {
            iconButtons.forEach { buttonData ->
                IconButton(onClick = { navController.navigate(buttonData.route) }) {
                    Icon(imageVector = buttonData.icon, contentDescription = buttonData.description)
                }
            }
        }
    }
}

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }) {
        Text(
            fontSize = 24.sp,
            text = "+"
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues, clickCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        Text(text = "Contenido de la pantalla de inicio")
        Text(text = "Botón pulsado: $clickCount veces")
    }
}


@Composable
fun BuildScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Build Screen")
    }
}

@Composable
fun MenuScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Menu Screen")
    }
}

@Composable
fun FavoriteScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Favorite Screen")
    }
}

@Composable
fun DeleteScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Delete Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio6Theme {
        HomeScreen(rememberNavController())
    }
}
