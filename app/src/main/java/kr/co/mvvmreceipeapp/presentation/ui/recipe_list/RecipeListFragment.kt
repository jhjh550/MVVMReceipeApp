package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.mvvmreceipeapp.presentation.components.CircularIndeterminateProgressBar
import kr.co.mvvmreceipeapp.presentation.components.RecipeCard
import kr.co.mvvmreceipeapp.presentation.components.SearchAppBar

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {
                val keyboardController = LocalSoftwareKeyboardController.current

                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                
                Scaffold(
                    topBar = {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            scrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                        )
                    },
                    bottomBar = {

                    },
                    drawerContent = {

                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.surface)
                    ) {
                        if (loading) {
                            // LoadingRecipeListShimmer(imageHeight = 250.dp)
                        }
                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe) {
                                }
                            }
                        }
                        CircularIndeterminateProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }
}


@Composable
fun GradientDemo(){
    val colors = listOf(Color.Blue, Color.Red, Color.Blue)
    val brush = linearGradient(
        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f)
    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(modifier = Modifier
            .fillMaxSize()
            .background(brush = brush))
    }
}








