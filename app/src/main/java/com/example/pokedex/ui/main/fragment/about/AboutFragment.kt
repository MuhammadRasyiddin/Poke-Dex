package com.example.pokedex.ui.main.fragment.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedex.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        Glide.with(this)
            .load(R.drawable.profile)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgProfileAbout)

        txtNameAbout.text = "Muhammad Rasyiddin"
        txtEmailAbout.text = "shinryuuzaki.kazami@gmail.com"
        txtPhoneAbout.text = "082112908141"
        txtDicodingAbout.text = "DICODING"
    }


}
