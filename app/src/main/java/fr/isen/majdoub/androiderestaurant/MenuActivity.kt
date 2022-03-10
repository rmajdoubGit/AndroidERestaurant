package fr.isen.majdoub.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import fr.isen.majdoub.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
  // @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        var array_food: kotlin.Array<out String> = resources.getStringArray(R.array.starter_list)
        var details_food: kotlin.Array<out String> = resources.getStringArray(R.array.starter_details)

        val str= intent.getStringExtra(HomeActivity.CATEGORY_KEY)
        binding.categoryTitle.text = str

        if(str=="Entrées"){
            array_food = resources.getStringArray(R.array.starter_list)
            details_food = resources.getStringArray(R.array.starter_details)
        }
        else if (str=="Plats"){
            array_food = resources.getStringArray(R.array.dish_list)
            details_food = resources.getStringArray(R.array.dish_details)
        }
        else if (str=="Desserts"){
            array_food = resources.getStringArray(R.array.dessert_list)
            details_food = resources.getStringArray(R.array.dessert_details)
        }

        // getting the recyclerview by its id
        //val recyclerview = findViewById<RecyclerView>(R.id.ListCategory)
        var adapter =CategoryAdapter(array_food)
        // this creates a vertical layout Manager
        binding.ListCategory.layoutManager = LinearLayoutManager(this)
        binding.ListCategory.adapter = adapter

        adapter.setOnItemClickListener(object : CategoryAdapter.onItemClickListener{
          override fun onItemClick(position: Int) {

              val url = "http://test.api.catering.bluecodegames.com/menu"
              val obj = JSONObject()
              obj.put("id_shop",1)
              val queue = Volley.newRequestQueue(this@MenuActivity)

              val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, obj,
                  { response ->
                      //textView.text = "Response: %s".format(response.toString())
                      Log.d("MenuActivity","Api call successful")
                  },
                  { error ->
                      // TODO: Handle error
                      Log.d("MenuActivity","Api call failed")
                  }
              )
              queue.add(jsonObjectRequest)

              //Toast.makeText(this@MenuActivity,"You cliked on item .$position",Toast.LENGTH_SHORT).show()
              val intent = Intent(this@MenuActivity, DetailsActivity::class.java)
              intent.putExtra(TITLE_KEY,array_food[position])
              intent.putExtra(DETAILS_KEY,details_food[position])
              startActivity(intent)


          }

      })

    }

    companion object{
        const val TITLE_KEY = "title"
        const val DETAILS_KEY = "details"
    }
}


