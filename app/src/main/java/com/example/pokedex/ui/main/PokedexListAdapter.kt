package com.example.pokedex.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Type
import com.example.pokedex.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_pokedex_list.view.*

class PokedexListAdapter(
    private val pokemonList: MutableList<Pokemon> = mutableListOf(),
    private val onClickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokedexListAdapter.PokedexListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokedexListAdapter.PokedexListViewHolder {
        return PokedexListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokedexListAdapter.PokedexListViewHolder, position: Int) {
        val pokemon: Pokemon = pokemonList[position]
        holder.bind(pokemon)
        addList(null)
    }

    inner class PokedexListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val typeAdapter = PokemonTypesAdapter()

        fun loadImage(url: String) {
            Glide.with(view)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside()
                .into(view.imgPokemon)
        }

        fun bind(pokemon: Pokemon) {
            val typesString: String = view.context.getJsonFromRaw(R.raw.rawtypes)
            var typeList = Gson().fromJson(typesString, Array<Type>::class.java).toList()
            val pokemonTypes = pokemon.types
            with(view) {
                val data = pokemonTypes?.map { // data yang ini
                        g ->
                    typeList.find { it.id == g } as Type
                }
                typeAdapter.addItems(data)
                recyclerTypePokemon.apply {
                    adapter = typeAdapter
                    layoutManager =
                        LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
                }
            }

            loadImage(pokemon.imageUrl as String)
            view.txtPokemonName.text = pokemon.name
            view.setOnClickListener {
                onClickListener(pokemon)
            }
        }
    }

    fun addList(list: List<Pokemon>?) {
        list?.let { pokemon ->
            pokemonList.addAll(pokemon)
        }
    }
}