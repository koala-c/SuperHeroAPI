package com.example.superheroapi_denisegalloni.superheroapp

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("image") val superHeroImage: SuperHeroImageResponse
)

data class SuperHeroImageResponse(
    @SerializedName("url") val url: String
)