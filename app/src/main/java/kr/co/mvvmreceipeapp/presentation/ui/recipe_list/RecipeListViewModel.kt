package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.mvvmreceipeapp.domain.model.Recipe
import kr.co.mvvmreceipeapp.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
): ViewModel(){

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    val loading = mutableStateOf(false)

    init {
        newSearch()
    }

    fun newSearch(){
        viewModelScope.launch {
            loading.value = true
            resetSearchState()

            delay(1000)

            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result

            loading.value = false
        }
    }

    private fun resetSearchState(){
        recipes.value = listOf()
        if(selectedCategory.value?.value != query.value){
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory(){
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int){
        categoryScrollPosition = position
    }
}
