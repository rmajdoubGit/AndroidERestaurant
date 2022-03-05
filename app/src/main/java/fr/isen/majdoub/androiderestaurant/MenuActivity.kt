package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.majdoub.androiderestaurant.databinding.ActivityCategoryBinding



class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
  // @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_configuraiton)
        val intent = intent

        //val textview : TextView = findViewById<Button>(R.id.Configtitle)
        val str= intent.getStringExtra(HomeActivity.CATEGORY_KEY)
        binding.categoryTitle.text = str

        // getting the recyclerview by its id
        //val recyclerview = findViewById<RecyclerView>(R.id.ListCategory)

        // this creates a vertical layout Manager
        binding.ListCategory.layoutManager = LinearLayoutManager(this)
       // binding.ListCategory.adapter = CategoryAdapter(arrayListOf(<String>()))

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel( str + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CategoryAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.ListCategory.adapter = adapter


    }
}


