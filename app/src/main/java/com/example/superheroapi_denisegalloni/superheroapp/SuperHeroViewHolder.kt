package com.example.superheroapi_denisegalloni.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi_denisegalloni.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName
        Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superHeroId) }
    }
}