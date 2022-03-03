package fr.isen.majdoub.androiderestaurant
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val mButtonStarter : Button = findViewById<Button>(R.id.StarterButton)
        val mButtonDish : Button = findViewById<Button>(R.id.DishButton)
        val mButtonDessert : Button = findViewById<Button>(R.id.DessertButton)

        mButtonStarter.setOnClickListener {
            chooseCatergory(getString(R.string.home_starter))
        }
        mButtonDish.setOnClickListener {
            chooseCatergory(getString(R.string.home_dish))
        }
        mButtonDessert.setOnClickListener {
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