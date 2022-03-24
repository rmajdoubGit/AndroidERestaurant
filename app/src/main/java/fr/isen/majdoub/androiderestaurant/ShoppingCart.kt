package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.io.File

@SuppressLint("StaticFieldLeak")
object ShoppingCart {

    var panier : Cart = Cart(arrayListOf())
    var content = panier.listItem

    fun updateCart(cartItem: ItemCart,context: Context) {
        if(cartItem.quantity==0){
            panier.listItem.remove(cartItem)
        }
        if(panier.listItem.any{it.name == cartItem.name}){
            val existElem = panier.listItem.first{it.name == cartItem.name}
            existElem.quantity += cartItem.quantity
            if(existElem.quantity<=0)
                panier.listItem.remove(existElem)
        }else{
            if(cartItem.quantity>0)
                panier.listItem.add(cartItem)
        }
        Log.i("Panier", panier.toString())
        Log.i("nombre total",panier.listItem.sumOf { it.quantity }.toString())
        saveCart(context)

    }

    @SuppressLint("CommitPrefEdits")
    fun saveCart ( context: Context){
        val panierJson : String = Gson().toJson(panier)
        val file = File(context.filesDir,"cart.json")
        file.writeText(panierJson)
        val objectSharedPreference : SharedPreferences = context.getSharedPreferences("cart",Context.MODE_PRIVATE)
        val editeur = objectSharedPreference.edit()
        editeur.putInt("nombre total",panier.listItem.sumOf { it.quantity })
        editeur.apply()
    }

    fun getCart(context: Context): Cart {
        //Creating a new Gson object to read data
        Log.d("fichier",context.filesDir.toString())
        val getJson = File(context.filesDir, "cart.json")
        val read = getJson.readText()
        panier = Gson().fromJson(read, Cart::class.java)
        return panier
    }

}