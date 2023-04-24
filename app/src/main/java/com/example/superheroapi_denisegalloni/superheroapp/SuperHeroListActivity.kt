package com.example.superheroapi_denisegalloni.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapi_denisegalloni.R
import com.example.superheroapi_denisegalloni.databinding.ActivitySuperHeroListBinding
import com.example.superheroapi_denisegalloni.superheroapp.DetailSuperHeroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperHeroAdapter { superHeroId -> navigateToDetail(superHeroId) }
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            // Crea la interfaç i comença el fil secundari
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful) {
                Log.i("koala", "Funciona :)")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("koala", response.toString())
                    // En el fil principal
                    runOnUiThread {
                        adapter.updateList(response.superHeroes)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("koala", "No funciona :(")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}