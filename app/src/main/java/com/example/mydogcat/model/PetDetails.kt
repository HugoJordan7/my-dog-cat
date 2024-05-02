package com.example.mydogcat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PetDetails(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val breeds: List<Breed>? = null
): Serializable

data class Breed(
    var height: Height? = null,
    var weight: Weight? = null,
    var name: String? = null,
    var temperament: String? = null,
    @SerializedName("bred_for") var bredFor: String? = null,
    @SerializedName("breed_group") var breedGroup: String? = null,
    @SerializedName("life_span") var lifeSpan: String? = null
)

data class Weight(
    var imperial: String? = null,
    var metric: String? = null
)

data class Height(
    var imperial: String? = null,
    var metric: String? = null
)
