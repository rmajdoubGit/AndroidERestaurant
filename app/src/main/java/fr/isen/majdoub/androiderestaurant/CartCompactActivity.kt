package fr.isen.majdoub.androiderestaurant

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


open class CartCompactActivity : AppCompatActivity() {
    var textCartItemCount: TextView? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val menuItem = menu!!.findItem(R.id.cart_action)
        val actionView = menuItem.actionView
        textCartItemCount = actionView.findViewById<View>(R.id.cart_badge) as TextView
        setupBadge()
        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =when(item.itemId) {
            R.id.cart_action -> {
                startActivity(Intent(this, CartActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun onResume() {
        super.onResume()
        setupBadge()
    }

    protected fun setupBadge() {
        if (textCartItemCount != null) {
            val mCartItemCount = getSharedPreferences(
                "cart",
                Context.MODE_PRIVATE
            ).getInt("nombre total", 0)
            Log.i("nombre total après lecture", mCartItemCount.toString())

            if (mCartItemCount == 0) {
                if (textCartItemCount!!.visibility != View.GONE) {
                    textCartItemCount!!.visibility = View.GONE
                }
            } else {
                textCartItemCount!!.text = java.lang.String.valueOf(mCartItemCount.coerceAtMost(99))
                if (textCartItemCount!!.visibility != View.VISIBLE) {
                    textCartItemCount!!.visibility = View.VISIBLE
                }
            }
        }

    }

}

