package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import kr.co.mvvmreceipeapp.presentation.components.*
import kr.co.mvvmreceipeapp.presentation.components.HeartAnimationDefinition.HeartButtonState
import kr.co.mvvmreceipeapp.presentation.components.HeartAnimationDefinition.HeartButtonState.*

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

                Column {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                    )
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        val state = remember{ mutableStateOf(IDLE)}

                        AnimatedHeartButton(modifier = Modifier, buttonState = state) {
                            state.value = if(state.value == IDLE) ACTIVE else IDLE
                        }
                    }


                    
//                    PulsingDemo()

//                    Box(modifier = Modifier.fillMaxWidth()){
//                        LazyColumn{
//                            itemsIndexed(
//                                items = recipes
//                            ){ index, recipe ->
//                                RecipeCard(recipe) {
//                                }
//                            }
//                        }
//                        CircularIndeterminateProgressBar(isDisplayed = loading)
//                    }
                }
            }
        }
    }
}