package com.example.pokedex.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GenreItemDecoration(private val gap: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = gap
        outRect.bottom = gap
    }
}