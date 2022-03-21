package fr.isen.majdoub.androiderestaurant
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import fr.isen.majdoub.androiderestaurant.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.starterText.setOnClickListener {
            chooseCatergory(getString(R.string.home_starter))
        }
        binding.dishText.setOnClickListener {
            chooseCatergory(getString(R.string.home_dish))
        }
        binding.dessertText.setOnClickListener {
            chooseCatergory(getString(R.string.home_desert))
        }

    }

    override fun onStop(){
        super.onStop()
        Log.d("HomeActivity","L'activité est arrêtée")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity","L'activité est dértuite")
    }
    private fun chooseCatergory(string: String) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(CATEGORY_KEY,string)
        startActivity(intent)
    }
    companion object{
        const val CATEGORY_KEY = "category"
    }

}