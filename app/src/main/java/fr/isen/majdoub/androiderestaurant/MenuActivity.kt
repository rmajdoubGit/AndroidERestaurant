package fr.isen.majdoub.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.majdoub.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject
import kotlin.math.log


interface MyInterface{
    fun onCallback(response:JSONObject, done : Boolean)
}

class MenuActivity : AppCompatActivity(),MyInterface {

    private lateinit var binding: ActivityCategoryBinding
    lateinit var adapter: CategoryAdapter
    val myInterface = this
    lateinit var name : List<String>
    lateinit var price : List<String>
    lateinit var item : List<Items>
    lateinit var menu_item : Menu
    lateinit var menu : Data
    var done : Boolean =false
    var ingre : String =""

    override fun onCallback(response: JSONObject, done: Boolean) {
        if(done) {
            val str = intent.getStringExtra(HomeActivity.CATEGORY_KEY)
            binding.categoryTitle.text = str
            menu = Gson().fromJson(response.toString(), Data::class.java)
            if (str == "Entrées") {
                menu_item = menu.data[0]

            } else if (str == "Plats") {
                menu_item = menu.data[1]
                Log.i("image",menu_item.items[1].images[0])

            } else if (str == "Desserts") {
                menu_item = menu.data[2]
            }

            adapter = CategoryAdapter(this,menu_item)
            binding.ListCategory.layoutManager = LinearLayoutManager(this)
            binding.ListCategory.adapter = adapter

            adapter.setOnItemClickListener(object : CategoryAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MenuActivity, DetailsActivity::class.java)
                    for(indice in 0 until  menu_item.items[position].ingredients.size) {
                        if(indice==menu_item.items[position].ingredients.size-1) {
                            ingre += menu_item.items[position].ingredients[indice].name_fr
                        }
                        else {
                            ingre += menu_item.items[position].ingredients[indice].name_fr+","
                        }
                    }
                    intent.putExtra(TITLE_KEY,menu_item.items[position].name_fr)
                    intent.putExtra(DETAILS_KEY,ingre)
                    ingre=""
                    startActivity(intent)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val obj = JSONObject()
        obj.put("id_shop", "1")
        val queue = Volley.newRequestQueue(this@MenuActivity)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, obj,
            { response ->

                Log.d("Response","$response")
                Log.d("MenuActivity", "Api call succes")
                done = true
                myInterface.onCallback(response,done)
            },
            { error ->

                Log.d("MenuActivity", "Api call failed")
            }
        )
        queue.add(jsonObjectRequest)
    }

    companion object {
        const val TITLE_KEY = "title"
        const val DETAILS_KEY = "details"
    }



}


