package kr.co.mvvmreceipeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.mvvmreceipeapp.network.RecipeService
import kr.co.mvvmreceipeapp.network.model.RecipeDTOMapper
import kr.co.mvvmreceipeapp.repository.RecipeRepository
import kr.co.mvvmreceipeapp.repository.RecipeRepository_Impl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDTOMapper: RecipeDTOMapper
    ): RecipeRepository{
        return RecipeRepository_Impl(recipeService, recipeDTOMapper )
    }



}