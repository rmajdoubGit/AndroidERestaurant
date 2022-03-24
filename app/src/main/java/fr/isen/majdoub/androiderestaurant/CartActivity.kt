package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.majdoub.androiderestaurant.databinding.ActivityCartBinding

class CartActivity : CartCompactActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listCart.layoutManager = LinearLayoutManager(this)
        binding.listCart.adapter = CartAdapter(ShoppingCart.getCart(this)){ itCart ->
            val item  = itCart.copy()
            item.quantity = -1
            ShoppingCart.updateCart(item,this)
            setupBadge()
            binding.listCart.adapter?.notifyDataSetChanged()

        }
    }
}