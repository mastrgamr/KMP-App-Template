package com.jetbrains.kmpapp

//import com.jetbrains.kmpapp.screens.list.viewModelFactoryOwner
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.kmpapp.di.appViewModelFactoryOwner
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.detail.viewModelFactoryOwner
import com.jetbrains.kmpapp.screens.list.ListScreen
import com.jetbrains.kmpapp.screens.list.viewModelFactoryOwner
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.LocalViewModelFactoryOwner
import kotlinx.serialization.Serializable

//private val Unit.viewModelFactoryOwner: ViewModelFactoryOwner

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val objectId: Int)

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(
                LocalViewModelFactoryOwner provides appComponent.appViewModelFactoryOwner,
            ) {
                NavHost(navController = navController, startDestination = ListDestination) {
                    composable<ListDestination> {
                        CompositionLocalProvider(
                            LocalViewModelFactoryOwner provides listComponent!!.viewModelFactoryOwner,
                        ) {
                            ListScreen(navigateToDetails = { objectId ->
                                navController.navigate(DetailDestination(objectId))
                            })
                        }
                    }
                    composable<DetailDestination> { backStackEntry ->
                        CompositionLocalProvider(
                            LocalViewModelFactoryOwner provides detailComponent!!.viewModelFactoryOwner,
                        ) {
                            DetailScreen(
                                objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                                navigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
