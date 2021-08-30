package kr.co.mvvmreceipeapp.network.responses

import com.google.gson.annotations.SerializedName
import kr.co.mvvmreceipeapp.network.model.RecipeNetworkEntity

class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity>

    )