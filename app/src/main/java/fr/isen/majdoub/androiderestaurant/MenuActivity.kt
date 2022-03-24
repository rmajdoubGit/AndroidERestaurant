package fr.isen.majdoub.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.majdoub.androiderestaurant.databinding.ActivityMenuBinding
import org.json.JSONObject


class MenuActivity : CartCompactActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val title = intent.getStringExtra(HomeActivity.CATEGORY_KEY) ?: ""
        binding.categoryTitle.text = title

        when(title) {
            getString(R.string.home_starter) -> binding.iconItem.setImageResource(R.drawable.starter)
            getString(R.string.home_dish) -> binding.iconItem.setImageResource(R.drawable.plat)
            getString(R.string.home_desert) -> binding.iconItem.setImageResource(R.drawable.dessert)
        }
        binding.ListCategory.layoutManager = LinearLayoutManager(this)
        binding.ListCategory.adapter = CategoryAdapter(arrayListOf()) {}

        loadItemsFromServer(title)
    }

    private fun loadItemsFromServer(category : String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val obj = JSONObject()
        obj.put("id_shop", "1")
        val queue = Volley.newRequestQueue(this@MenuActivity)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, obj,
            { response ->
                val menu = Gson().fromJson(response.toString(), Data::class.java)
                val items = menu.data.firstOrNull{ it.name_fr == category }?.items ?: arrayListOf()
                val adapter = CategoryAdapter(items) {
                    val intent = Intent(this@MenuActivity, DetailsActivity::class.java)
                    intent.putExtra(DETAILS_KEY, it)
                    startActivity(intent)
                }
                binding.loaderIcon.visibility = View.GONE
                binding.ListCategory.adapter = adapter

            },
            { error ->
                Log.d("Error", "$error")
            }
        )
        queue.add(jsonObjectRequest)
    }

    companion object {
        const val DETAILS_KEY = "details"
    }
}


