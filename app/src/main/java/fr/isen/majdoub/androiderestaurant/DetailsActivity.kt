package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import fr.isen.majdoub.androiderestaurant.databinding.ActivityDetailsBinding

class DetailsActivity : CartCompactActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        var quantity : Int= 1
        val item = intent.getSerializableExtra(MenuActivity.DETAILS_KEY) as Items

        binding.foodTitle.text = item.name_fr
        binding.detailsFood.text = item.ingredients.joinToString(", ", transform = { it.name_fr })
        binding.buttonPrice.text = "Ajouter au panier : "+item.prices[0].price + "€"

        binding.buttonPLus.setOnClickListener{
            quantity++
            Log.i("quantity",quantity.toString())
            display(quantity,quantity*item.prices[0].price.toFloat())

        }
        binding.buttonMine.setOnClickListener{
            if(quantity==1)
            else {
                quantity--
            }
            display(quantity,quantity*item.prices[0].price.toFloat())
        }

        binding.buttonPrice.setOnClickListener{

            ShoppingCart.updateCart(ItemCart(item.images[0],item.name_fr,quantity,item.prices[0].price.toFloat()),this)
            setupBadge()
            Snackbar.make(binding.root,"$quantity ${item.name_fr} bien ajouté au panier", Snackbar.LENGTH_SHORT ).show()
        }

        binding.viewSilder.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewSilder.adapter = ViewAdapter(this,item.images)

    }
    @SuppressLint("SetTextI18n")
    private fun display(quantity : Int, price : Float){
        binding.itemQuantity.text=quantity.toString()
        binding.buttonPrice.text = "Ajouter au panier : "+price.toString()+"€"
    }

}