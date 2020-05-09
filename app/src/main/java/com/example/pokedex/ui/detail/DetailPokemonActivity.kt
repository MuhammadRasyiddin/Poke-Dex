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
import com.example.pokedex.model.Type
import com.example.pokedex.ui.main.GenreItemDecoration
import com.example.pokedex.ui.main.PokemonTypesAdapter
import com.example.pokedex.utils.getJsonFromRaw
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import kotlinx.android.synthetic.main.template_toolbar.*

class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var pokemon: Pokemon
    private lateinit var pokemonTypesAdapter: PokemonTypesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pokemon)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pokemonTypesAdapter = PokemonTypesAdapter(mutableListOf())
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

        recyclerTypePokemonDetail.apply {
            adapter = pokemonTypesAdapter
            layoutManager = FlexboxLayoutManager(this@DetailPokemonActivity).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            addItemDecoration(GenreItemDecoration(3))
        }

        txtPokemonNameDetail.text = pokemon.name
        val pokemonTypes: List<Int>? = pokemon.types
        val typesString: String = getJsonFromRaw(R.raw.rawtypes)
        val typeList = Gson().fromJson(typesString, Array<Type>::class.java).toList()
        val types = pokemonTypes?.map { g ->
            typeList.find { it.id == g } as Type
        }
        pokemonTypesAdapter.addItems(types)
        txtWeightValueDetail.text = pokemon.weight
        txtHeightValueDetail.text = pokemon.height
        txtPokemonDescriptionDetail.text = pokemon.description
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
