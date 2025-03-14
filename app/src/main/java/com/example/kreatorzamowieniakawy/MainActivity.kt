package com.example.kreatorzamowieniakawy

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val myTextView = findViewById<TextView>(R.id.myTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                myTextView.text = "Ilosc: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        val myImageView: ImageView = findViewById(R.id.my_ImageView)

        val images = listOf(
            R.drawable.capuccino,
            R.drawable.espresso,
            R.drawable.latte
        )

        val radioGroup = findViewById<RadioGroup>(R.id.kawa_radiogroup)
        var selectedCoffee = ""

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.espresso_radiobutton -> {
                    myImageView.setImageResource(images[1])
                    selectedCoffee = "Espresso"
                }

                R.id.cappuccino_radiobutton -> {
                    myImageView.setImageResource(images[0])
                    selectedCoffee = "Cappuccino"
                }

                R.id.latte_radiobutton -> {
                    myImageView.setImageResource(images[2])
                    selectedCoffee = "Latte"
                }
            }
        }

        val myCheckBox_mleko = findViewById<CheckBox>(R.id.mleko)
        var milkSelected = "Nie zaznaczone"
        var sugarSelected = "Nie zaznaczone"

        myCheckBox_mleko.setOnCheckedChangeListener { _, isChecked ->
            milkSelected = if (isChecked) "Mleko" else "Brak mleka"
        }
        val myCheckBox_cukier = findViewById<CheckBox>(R.id.cukier)
        myCheckBox_cukier.setOnCheckedChangeListener { _, isChecked ->
            sugarSelected = if (isChecked) "Cukier" else "Brak cukru"
        }

        val submitButton = findViewById<Button>(R.id.myButton)

        submitButton.setOnClickListener{
            val seekBarValue=seekBar.progress
            val message = "Wybrana kawa: $selectedCoffee\nMleko: $milkSelected\nCukier: $sugarSelected\nIlosc: $seekBarValue"
            Toast.makeText(this@MainActivity, "Pobrano dane", Toast.LENGTH_LONG).show()
            Log.d("kawa",message)
        }
    }
}