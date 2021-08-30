package kr.co.mvvmreceipeapp.repository

import kr.co.mvvmreceipeapp.domain.model.Recipe
import kr.co.mvvmreceipeapp.network.RecipeService
import kr.co.mvvmreceipeapp.network.model.RecipeDTOMapper

class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDTOMapper
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        val result = recipeService.get(token, id)
        return mapper.mapToDomainModel(result)
    }
}