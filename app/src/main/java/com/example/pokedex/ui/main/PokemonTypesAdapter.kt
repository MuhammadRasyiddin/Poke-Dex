package com.example.pokedex.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Type
import kotlinx.android.synthetic.main.item_type_pokemon.view.*

class PokemonTypesAdapter(
    private val pokemonTypesList: MutableList<Type> = mutableListOf()
) : RecyclerView.Adapter<PokemonTypesAdapter.PokemonTypesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonTypesAdapter.PokemonTypesViewHolder {
        return PokemonTypesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_type_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonTypesList.size
    }

    override fun onBindViewHolder(
        holder: PokemonTypesAdapter.PokemonTypesViewHolder,
        position: Int
    ) {
        holder.bind(pokemonTypesList[position])
    }

    fun addItems(data: List<Type>?) {
        pokemonTypesList.clear()
        data?.let {
            pokemonTypesList.addAll(it)
        }
        notifyDataSetChanged()
    }

    inner class PokemonTypesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(type: Type) {
            view.txtTypePokemon.text = type.name
        }
    }

}