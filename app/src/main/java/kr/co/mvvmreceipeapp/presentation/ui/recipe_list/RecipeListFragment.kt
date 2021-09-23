package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.mvvmreceipeapp.presentation.components.FoodCategoryChip
import kr.co.mvvmreceipeapp.presentation.components.RecipeCard

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

                Column {
                    Surface(
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    value = query,
                                    onValueChange = { newValue ->
                                        viewModel.onQueryChanged(newValue)
                                    },
                                    label = {
                                        Text(text="search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search, contentDescription = "Search")
                                    },
                                    keyboardActions = KeyboardActions(
                                        onSearch = {
                                            viewModel.newSearch()
                                            keyboardController?.hide()
                                        }
                                    ),
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = MaterialTheme.colors.surface
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, bottom = 8.dp)
                            ){
                                for(category in getAllFoodCategories()){
                                    FoodCategoryChip(
                                        category = category.value,
                                        isSelected = selectedCategory == category,
                                        onSelectedCategoryChanged = {
                                            viewModel.onSelectedCategoryChanged(it)
                                        },
                                        onExecuteSearch = viewModel::newSearch

                                    )
                                }
                            }
                        }

                    }

                    LazyColumn{
                        itemsIndexed(
                            items = recipes
                        ){ index, recipe ->
                            RecipeCard(recipe) {
                            }
                        }
                    }
                }
            }
        }
    }
}