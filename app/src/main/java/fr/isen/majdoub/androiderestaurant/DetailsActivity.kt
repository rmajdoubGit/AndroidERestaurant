package fr.isen.majdoub.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import fr.isen.majdoub.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.majdoub.androiderestaurant.databinding.ActivityHomeBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        binding.foodTitle.text = intent.getStringExtra(MenuActivity.TITLE_KEY)
        val item = intent.getSerializableExtra(MenuActivity.DETAILS_KEY) as Items


        binding.detailsFood.text = item.ingredients.joinToString(", ")

        Log.i("image", item.images.toString())
        binding.viewSilder.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //binding.viewSilder.adapter = ViewAdapter(this,item.images)

    }
}