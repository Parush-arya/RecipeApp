package com.example.motamaker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState: CategoriesData,
    navigateToCategory: (Category) -> Unit
) {
    val categoriesState = viewState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            categoriesState.isLoading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }

            categoriesState.status == 500 -> {
                Text("Error Occured")
            }

            else -> {
                CategoryScreen(
                    categories = categoriesState.categories,
                    navigateToCategory
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToCategory: (Category) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) {
            CategoryItem(category = it, navigateToCategory)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigateToCategory: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigateToCategory(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1.0f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

    }
}
