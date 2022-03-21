package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import java.io.File

@SuppressLint("StaticFieldLeak")
object ShoppingCart {
    /*
    var weight = 0
        get() = content.sumOf { it.quantity }
    var panier : Cart = Cart(arrayListOf())
    var content = panier.listItem
    private var init = 0

     */
    private var init = 0
    private var panier : Cart = Cart(arrayListOf())


    fun addItem(cartItem: ItemCart,context: Context) {
        val cart : MutableList<ItemCart>
        if(init==0){
            init=1
            cart = arrayListOf()
        }
        else {
             cart = ShoppingCart.getCart(context).listItem
        }
        val targetItem = cart.singleOrNull { it.name == cartItem.name }
        if (targetItem == null) {
            cartItem.price = cartItem.price*cartItem.quantity
            cart.add(cartItem)
        } else {
                targetItem.quantity += cartItem.quantity
                targetItem.price = cartItem.price * targetItem.quantity
        }
        panier.listItem = cart
        saveCart(panier,context)
    }


     fun saveCart (panier : Cart, context: Context){
        val panierJson : String = Gson().toJson(panier)
        val file = File(context.filesDir,"cart1.json")
        file.writeText(panierJson)
    }

     fun getCart(context: Context) : Cart{
         //Creating a new Gson object to read data
         val getJson = File(context.filesDir,"cart1.json")
         val read = getJson.readText()
         val cart = Gson().fromJson(read,Cart::class.java)
         return cart

     }


}