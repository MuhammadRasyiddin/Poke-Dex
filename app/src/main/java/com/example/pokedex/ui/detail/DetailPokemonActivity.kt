package com.example.pokedex.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import kotlinx.android.synthetic.main.item_pokedex_grid.imgPokemon
import kotlinx.android.synthetic.main.template_toolbar.*

class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var pokemon: Pokemon
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pokemon)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(POKEMON_KEY)) {
            pokemon = intent.getParcelableExtra(POKEMON_KEY) as Pokemon
        } else {
            finish()
        }

        Glide.with(this)
            .load(pokemon.imageUrl)
            .centerInside()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgPokemon)

        txtPokemonName.text = pokemon.name
        txtWeightValue.text = pokemon.weight
        txtHeightValue.text = pokemon.height
        txtPokemonDescription.text = pokemon.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetailPokemonActivity::class.java)
        }

        const val POKEMON_KEY = "POKEMON_KEY"
    }

}
