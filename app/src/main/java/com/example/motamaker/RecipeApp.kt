package com.example.motamaker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(modifier: Modifier, navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.responseState
    NavHost(navController, Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(Modifier, viewState, {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.CategoryDetailScreen.route)
            })
        }

        composable(Screen.CategoryDetailScreen.route) {
            CategoryDetailScreen(
                category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>(
                    "cat"
                ) ?: Category("", "", "", "")
            )

        }
    }
}