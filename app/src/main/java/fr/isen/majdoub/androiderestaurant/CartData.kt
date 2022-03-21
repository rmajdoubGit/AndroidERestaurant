package fr.isen.majdoub.androiderestaurant

import java.io.Serializable

data class Cart(

    var listItem : MutableList<ItemCart>
) : Serializable

data class ItemCart(

    var name : String,
    var quantity : Int,
    var price : Float
): Serializable


/*{
    override fun toString(): String {
        return "Item [name: ${this.name}, quantity: ${this.quantity}, price: ${this.price}]"
    }
}

 */
