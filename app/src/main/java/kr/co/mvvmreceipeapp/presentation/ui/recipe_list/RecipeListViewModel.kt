package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.mvvmreceipeapp.domain.model.Recipe
import kr.co.mvvmreceipeapp.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
): ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    init{
        newSearch()
    }

    fun newSearch(){
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = "chicken"
            )
            recipes.value = result
        }
    }
}