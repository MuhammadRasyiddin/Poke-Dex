package com.example.pokedex.ui.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.detail.DetailPokemonActivity
import com.example.pokedex.ui.main.PokedexListAdapter
import com.example.pokedex.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val pGridAdapter = PokedexListAdapter(
            onClickListener = { pokemon ->
                val intent = DetailPokemonActivity.newIntent(requireContext())
                intent.putExtra(DetailPokemonActivity.POKEMON_KEY, pokemon)
                startActivity(intent)
            }
        )
        recyclerPokedex.apply {
            adapter = pGridAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        val pokemonString: String = requireContext().getJsonFromRaw(R.raw.pokedex)
        val pokemonList = Gson().fromJson(pokemonString, Array<Pokemon>::class.java).toList()
        pGridAdapter.addList(pokemonList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
