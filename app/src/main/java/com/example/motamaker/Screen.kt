package com.example.motamaker

sealed class Screen(val route: String) {
    object RecipeScreen : Screen("recipeScreen")
    object CategoryDetailScreen : Screen("categoryDetailScreen")
}