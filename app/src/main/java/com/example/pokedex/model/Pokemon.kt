package com.example.pokedex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("NationalId")
    var nationalId: String? = null,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Height")
    var height: String? = null,
    @SerializedName("Weight")
    var weight: String? = null,
    @SerializedName("Description")
    var description: String? = null,
    @SerializedName("ImageUrl")
    var imageUrl: String? = null
) : Parcelable