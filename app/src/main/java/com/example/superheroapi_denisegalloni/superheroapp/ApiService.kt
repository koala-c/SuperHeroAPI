package com.example.superheroapi_denisegalloni.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/5928571000525027/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>

    @GET("/api/5928571000525027/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superheroId:String):Response<SuperHeroDetailResponse>
}