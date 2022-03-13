package fr.isen.majdoub.androiderestaurant

import com.google.gson.annotations.SerializedName

data class Data(
    val data : List<Menu>
)
data class Menu(
    @SerializedName("name_fr")
    val name_fr: String,
    @SerializedName("name_en")
    val name_en : String,
    @SerializedName("items")
    val items : List<Items>
)

data class Items (
    @SerializedName("name_fr")
    val name_fr : String,
    @SerializedName("images")
    val images : List<String>,
    @SerializedName("ingredients")
    val ingredients : List<Ingredients>,
    @SerializedName("prices")
    val prices : ArrayList<Prices>
)

data class Prices(
    @SerializedName("price")
    val price: String
)

data class Ingredients(
    @SerializedName("name_fr")
    val name_fr: String
)
