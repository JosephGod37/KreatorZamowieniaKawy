package com.example.kreatorzamowieniakawy

import android.os.Bundle
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
                // Ta metoda wywoływana jest w momencie, gdy użytkownik dotknie i zacznie przesuwać suwak
                Toast.makeText(this@MainActivity, "Zacząłeś przesuwać suwak", Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Ta metoda wywoływana jest w momencie, gdy użytkownik przestanie przesuwać suwak i puści go
                Toast.makeText(this@MainActivity, "Zakończyłeś przesuwanie suwaka", Toast.LENGTH_SHORT).show()
            }
        })

        val myImageView: ImageView = findViewById(R.id.my_ImageView)

        val images = listOf(
            R.drawable.capuccino,
            R.drawable.espresso,
            R.drawable.latte
        )

        val radioGroup = findViewById<RadioGroup>(R.id.kawa_radiogroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.espresso_radiobutton -> {
                    myImageView.setImageResource(images[1])
                }

                R.id.cappuccino_radiobutton -> {
                    myImageView.setImageResource(images[0])
                }

                R.id.latte_radiobutton -> {
                    myImageView.setImageResource(images[2])
                }
            }
        }


    }
}