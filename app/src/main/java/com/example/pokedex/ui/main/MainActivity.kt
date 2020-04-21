package com.example.pokedex.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.detail.DetailPokemonActivity
import com.example.pokedex.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        val pGridAdapter = PokedexGridAdapter(
            onClickListener = { pokemon ->
                val intent = DetailPokemonActivity.newIntent(this)
                intent.putExtra(DetailPokemonActivity.POKEMON_KEY, pokemon)
                startActivity(intent)
            }
        )
        recyclerPokedex.apply {
            adapter = pGridAdapter
            layoutManager = GridLayoutManager(this.context, 3)
        }

        val pokemonString: String = getJsonFromRaw(R.raw.pokedex)
        val pokemonList = Gson().fromJson(pokemonString, Array<Pokemon>::class.java).toList()
        pGridAdapter.addList(pokemonList)
    }
}
