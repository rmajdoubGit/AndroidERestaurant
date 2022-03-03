package fr.isen.majdoub.androiderestaurant

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuraiton)
        val intent = intent
        if (intent != null) {
            val textview : TextView = findViewById<Button>(R.id.Configtitle)
            val str= intent.getStringExtra("config")
            textview.text = str



            }
            }
        }


