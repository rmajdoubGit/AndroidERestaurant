package fr.isen.majdoub.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            binding.detailsFood.text =intent.getStringExtra(MenuActivity.DETAILS_KEY)


    }
}