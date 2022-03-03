package fr.isen.majdoub.androiderestaurant
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val mButtonStarter : Button = findViewById<Button>(R.id.StarterButton)
        val mButtonDish : Button = findViewById<Button>(R.id.DishButton)
        val mButtonDessert : Button = findViewById<Button>(R.id.DessertButton)

        mButtonStarter.setOnClickListener {
            chooseCatergory("starter")
        }
        mButtonDish.setOnClickListener {
            chooseCatergory("dishes")
        }
        mButtonDessert.setOnClickListener {
            chooseCatergory("dessert")
        }

    }
    private fun chooseCatergory(string: String) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("config",string)
        startActivity(intent)
    }

}