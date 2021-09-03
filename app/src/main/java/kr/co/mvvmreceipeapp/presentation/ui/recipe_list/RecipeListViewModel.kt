package kr.co.mvvmreceipeapp.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.mvvmreceipeapp.network.model.RecipeDTOMapper
import kr.co.mvvmreceipeapp.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel() {

    init {
        println("ViewModel : $randomString")
        println("ViewModel : $repository")
        println("ViewModel : $token")
    }

    fun getRepo() = repository

    fun getRandomString() = randomString

    fun getToken() = token
}