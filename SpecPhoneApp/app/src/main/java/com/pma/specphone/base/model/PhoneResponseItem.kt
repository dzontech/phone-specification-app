package com.pma.specphone.base.model


import com.google.gson.annotations.SerializedName

data class PhoneResponseItem(
    @SerializedName("announced")
    val announced: String,
    @SerializedName("approx_price_EUR")
    val approxPriceEUR: String,
    @SerializedName("battery")
    val battery: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("CPU")
    val cPU: String,
    @SerializedName("Chipset")
    val chipset: String,
    @SerializedName("colors")
    val colors: String,
    @SerializedName("dimentions")
    val dimentions: String,
    @SerializedName("id")
    val id: Int,
    //@SerializedName("img_url")
    val img_url: String,
    @SerializedName("internal_memory")
    val internalMemory: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("OS")
    val oS: String,
    @SerializedName("primary_camera")
    val primaryCamera: String,
    @SerializedName("RAM")
    val rAM: String,
    @SerializedName("SIM")
    val sIM: String,
    @SerializedName("secondary_camera")
    val secondaryCamera: String,
    @SerializedName("weight_g")
    val weightG: String
)